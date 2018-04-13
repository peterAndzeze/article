package com.article.recommend.recommend.baseiterm;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.entity.DictionaryInfo;
import com.article.recommend.recommend.RecommendFactory;
import com.article.recommend.recommend.RecommendQueueVo;
import com.article.recommend.redis.RedisService;
import com.article.recommend.service.dictionary.DictionaryService;
import com.article.recommend.threadpool.ThreadFactoryService;
import com.article.recommend.threadpool.businessTask.RecommendValueTask;
import com.article.recommend.util.DateUtil;

/**
 * 基于文章
 */
@Service
public class ItermRecommend {
    private static Logger logger= LoggerFactory.getLogger(ItermRecommend.class);
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private ThreadFactoryService threadFactoryService;
    @Autowired
    private RedisService redisService;
    /**
     * 计算推荐结果
     * @param dataFile 数据文件
     */
    public void evaluateRecommend(String dataFile) throws IOException {
        DataModel dataModel=RecommendFactory.buildDateModel(dataFile);
        try {
        	logger.info("基于文章推荐 start");
            Map<Long,List<RecommendedItem>> dataMap=itemRecommend(dataModel);
            //过滤重复数据，并选择value值高的
            Long residueTime=DateUtil.getResidueMinutes();
            logger.info("基于文章redis有效时间还有{}分钟",residueTime);
            for (Map.Entry<Long,List<RecommendedItem>>  entry:dataMap.entrySet()){
            	redisService.putList("userid:"+entry.getKey(), entry.getValue(), residueTime, TimeUnit.MINUTES);
            }
        	logger.info("基于文章推荐 end");
        } catch (Exception e) {
        	logger.error("基于文章推荐计算异常:{}",e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 基于文章的PEARSON 指定距离内N个文章推荐
     * @param dataModel
     */
    private  Map<Long,List<RecommendedItem>> itemRecommend(DataModel dataModel) throws TasteException, IOException {
        //查询各个开关
        DictionaryInfo dictionaryInfo=dictionaryService.getDictionaryByKey(RecommendConstant.USER_SIM_STATE);
        DictionaryInfo numD=dictionaryService.getDictionaryByKey(RecommendConstant.RECOMMENDER_NUM);
        String[] states=dictionaryInfo.getValue().split(",");
        int recommenderNum=Integer.valueOf(numD.getValue());
        LongPrimitiveIterator userIds=dataModel.getUserIDs();//得到所有文章
        logger.info("基于文章推荐start");
        List<Recommender> useRecommenders=new ArrayList<>();
        Recommender recommender0=null;
        List<RecommendQueueVo> recommendQueueVos=new ArrayList<>(6);//最多6个
       if(states[0].equals(RecommendConstant.SYSTEM_DATE_EFFECTIVE)){//开 荡入数组
           recommender0=ItermRecommender.itemPearson(dataModel,recommendQueueVos);
           useRecommenders.add(recommender0);
           logger.info("基于文章推荐皮尔逊 end");
       }
        Recommender recommender1=null;
       if(states[1].equals(RecommendConstant.SYSTEM_DATE_EFFECTIVE)) {
           recommender1= ItermRecommender.itemCosin(dataModel,recommendQueueVos);
           useRecommenders.add(recommender1);
           logger.info("基于文章推荐cosin end");
       }
        Recommender recommender2=null;
       if(states[2].equals(RecommendConstant.SYSTEM_DATE_EFFECTIVE)) {
           recommender2 = ItermRecommender.itemTanimoto(dataModel,recommendQueueVos);
           useRecommenders.add(recommender2);
           logger.info("基于文章推荐TANIMOTO end");
       }
        Recommender recommender3=null;
       if(states[3].equals(RecommendConstant.SYSTEM_DATE_EFFECTIVE)) {
           recommender3= ItermRecommender.itemLoglikelihood(dataModel,recommendQueueVos);
           useRecommenders.add(recommender3);
           logger.info("基于文章推荐LOGLIKELIHOOD end");
       }
        Recommender recommender4=null;
       if(states[4].equals(RecommendConstant.SYSTEM_DATE_EFFECTIVE)) {
           recommender4= ItermRecommender.itemCityblock(dataModel,recommendQueueVos);
           useRecommenders.add(recommender4);
           logger.info("基于文章推荐CITYBLOCK end");
       }
        Recommender recommender5=null;
        if(states[5].equals(RecommendConstant.SYSTEM_DATE_EFFECTIVE)) {
            recommender5= ItermRecommender.itemEuclidean(dataModel,recommendQueueVos);
            useRecommenders.add(recommender5);
            logger.info("基于文章推荐EUCLIDEAN end");
       }
       Map<Long,List<RecommendedItem>> dataMap=new HashMap<>();
       logger.info("为文章{}从{}个选择start",dataModel.getNumUsers(),dataModel.getNumItems()); 
       for (Recommender recommender:useRecommenders) {//redis并发操作的话,可以
           while (userIds.hasNext()) {
               Long uId = userIds.next();
               List<RecommendedItem> list=result(recommender,uId,recommenderNum);//提前判断
               if(list.size()==0 || list.isEmpty()){
                   continue;
               }
               if(dataMap.containsKey(uId)){//存在
                  dataMap.get(uId).addAll(list);
               }else{//不存在
                   dataMap.put(uId,list);
               }
           }
       }
        logger.info("推荐结束");
       //扔到队列计算
        if(!recommendQueueVos.isEmpty()) {//不为空
        	try {
				threadFactoryService.executeSingleTask(recommendQueueVos,RecommendValueTask.class );
			} catch (Exception exception) {
				logger.info("基于文章计算得分放入线程池失败:{}",exception.getMessage());
				exception.printStackTrace();
			}
        }
       logger.info("协同率基于文章end");
       return dataMap;
    }

    /**
     * 每一个用户推荐多少
     * @param recommender
     * @param userId
     * @param recommendNum
     */
    public  List<RecommendedItem>  result(Recommender recommender,Long userId,int recommendNum) throws TasteException {
        return recommender.recommend(userId,recommendNum);
    }

    
   
    public static void main(String[] args) throws TasteException, IOException {
       List<String> aList=new ArrayList<>();
       System.out.println(aList.isEmpty());
       
       LocalTime midnight = LocalTime.MIDNIGHT;
       LocalDate today = LocalDate.now();
       LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
       LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);
       System.out.println(TimeUnit.MINUTES.toMinutes(Duration.between(LocalDateTime.now(), tomorrowMidnight).toMinutes()));
       
    }


}
