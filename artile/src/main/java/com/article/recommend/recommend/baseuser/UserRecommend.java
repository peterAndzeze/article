package com.article.recommend.recommend.baseuser;

import java.io.IOException;
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
 * 基于用户
 */
@Service
public class UserRecommend {
    private static Logger logger= LoggerFactory.getLogger(UserRecommend.class);
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
            //指定距离最近的N个用户 字典暂时查多次
            DictionaryInfo dictionaryInfo=dictionaryService.getDictionaryByKey(RecommendConstant.NEAREST_NUM);
            int nearNum=(dictionaryInfo.getValue() ==null || dictionaryInfo.getValue().equals(""))? 10:Integer.valueOf(dictionaryInfo.getValue());
            //对象数据占用估计很大
            Map<Long,List<RecommendedItem>> dataMap=userRecommend(dataModel,nearNum);
            Long residueTime=DateUtil.getResidueMinutes();
            for (Map.Entry<Long,List<RecommendedItem>>  entry:dataMap.entrySet()){
            	redisService.putList("userid:"+entry.getKey(), entry.getValue(),residueTime, TimeUnit.MINUTES);
            }
            logger.info("基于用户过滤推荐重复end");
        } catch (TasteException e) {
        	logger.error("基于用户推荐异常:{}",e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 基于用户的PEARSON 指定距离内N个用户推荐
     * @param dataModel
     */
    private  Map<Long,List<RecommendedItem>> userRecommend(DataModel dataModel ,int nearNum) throws TasteException, IOException {
        //查询各个开关
        DictionaryInfo dictionaryInfo=dictionaryService.getDictionaryByKey(RecommendConstant.USER_SIM_STATE);
        DictionaryInfo numD=dictionaryService.getDictionaryByKey(RecommendConstant.RECOMMENDER_NUM);
        String[] states=dictionaryInfo.getValue().split(",");
        int recommenderNum=Integer.valueOf(numD.getValue());
        LongPrimitiveIterator userIds=dataModel.getUserIDs();//得到所有用户
        logger.info("基于用户推荐start");
        List<Recommender> useRecommenders=new ArrayList<>();
        Recommender recommender0=null;
        List<RecommendQueueVo> recommendQueueVos=new ArrayList<>(6);//最多6个
       if(states[0].equals(RecommendConstant.SYSTEM_DATE_EFFECTIVE)){//开 荡入数组
           recommender0=UserRecommender.userPearson(dataModel,nearNum,recommendQueueVos);
           useRecommenders.add(recommender0);
           logger.info("基于用户推荐皮尔逊 end");
       }
        Recommender recommender1=null;
       if(states[1].equals(RecommendConstant.SYSTEM_DATE_EFFECTIVE)) {
           recommender1= UserRecommender.userCosin(dataModel, nearNum,recommendQueueVos);
           useRecommenders.add(recommender1);
           logger.info("基于用户推荐cosin end");
       }
        Recommender recommender2=null;
       if(states[2].equals(RecommendConstant.SYSTEM_DATE_EFFECTIVE)) {
           recommender2 = UserRecommender.userTanimoto(dataModel, nearNum,recommendQueueVos);
           useRecommenders.add(recommender2);
           logger.info("基于用户推荐TANIMOTO end");
       }
        Recommender recommender3=null;
       if(states[3].equals(RecommendConstant.SYSTEM_DATE_EFFECTIVE)) {
           recommender3= UserRecommender.userLoglikelihood(dataModel, nearNum,recommendQueueVos);
           useRecommenders.add(recommender3);
           logger.info("基于用户推荐LOGLIKELIHOOD end");
       }
        Recommender recommender4=null;
       if(states[4].equals(RecommendConstant.SYSTEM_DATE_EFFECTIVE)) {
           recommender4= UserRecommender.userCityblock(dataModel, nearNum,recommendQueueVos);
           useRecommenders.add(recommender4);
           logger.info("基于用户推荐CITYBLOCK end");
       }
        Recommender recommender5=null;
        if(states[5].equals(RecommendConstant.SYSTEM_DATE_EFFECTIVE)) {
            recommender5= UserRecommender.userEuclidean(dataModel, nearNum,recommendQueueVos);
            useRecommenders.add(recommender5);
            logger.info("基于用户推荐EUCLIDEAN end");
       }
       Map<Long,List<RecommendedItem>> dataMap=new HashMap<>();
       logger.info("为用户{}从{}个选择start",dataModel.getNumUsers(),dataModel.getNumItems()); 
       for (Recommender recommender:useRecommenders) {
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
				logger.info("基于用户计算得分放入线程池失败:{}",exception.getMessage());
				exception.printStackTrace();
			}
        }
       logger.info("协同率基于用户end");
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
    public static void main(String[] args) {
    	 /* List<Long> ids = new ArrayList<>(new ArrayList().size());//用来临时存储iterm的id
          //并行计算去重复
          List<RecommendedItem> newResult=new ArrayList().getValue().parallelStream().filter(v->{
             boolean flag=!ids.contains(v.getItemID());
             ids.add(v.getItemID());
             return flag;
          }).collect(Collectors.toList());
          redisMap.put(String.valueOf(entry.getKey()), JSON.toJSONString(newResult));*/
	}
}
