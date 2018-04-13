package com.article.recommend.threadpool.businessTask;

import org.apache.mahout.cf.taste.common.TasteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.entity.DictionaryInfo;
import com.article.recommend.recommend.EvaluateValueRecommend;
import com.article.recommend.recommend.RecommendQueueVo;
import com.article.recommend.service.dictionary.DictionaryService;
import com.article.recommend.threadpool.task.BaseTask;
import com.article.recommend.threadpool.task.ThreadContext;
import com.article.recommend.util.SpringUtil;


/**
 *推荐计算评分任务队列
 */
public class RecommendValueTask extends BaseTask {
	private static Logger logger=LoggerFactory.getLogger(RecommendValueTask.class);
    @Override
    public Object getSyncObject() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public void run(ThreadContext context) throws InterruptedException {
        RecommendQueueVo recommendQueueVo=(RecommendQueueVo) context.take();
        if(null==recommendQueueVo) {//逻辑判断（二次）
        	return;
        }
        String recommendType=recommendQueueVo.getRecommendType();
        String similarity=recommendQueueVo.getSimilarity();
        logger.info("基于{}相似度{}计算评分start",recommendType,similarity);
        DictionaryService dictionaryService=SpringUtil.getBean(DictionaryService.class);
        DictionaryInfo topDictionary=dictionaryService.getDictionaryByKey(RecommendConstant.TOP_NUM);
        //计算多少个推荐结果
        int topn=topDictionary.getValue()==null?2:Integer.valueOf(topDictionary.getValue());
        DictionaryInfo trainPtDic=dictionaryService.getDictionaryByKey(RecommendConstant.TRAINPT_NUM);
        //测试数据比例
        Double trainPt=trainPtDic.getValue()==null?0.9:Double.valueOf(trainPtDic.getValue());
        DictionaryInfo countPreInfo=dictionaryService.getDictionaryByKey(RecommendConstant.TOTAL_NUM);
        //总数比例
        Double countPre=countPreInfo.getValue()==null?1.0:Double.valueOf(countPreInfo.getValue());
        logger.info("基于用户计算推荐评分，总数据量{},取推荐个数{},测试数据比例{}",countPre,topn,trainPt);
        EvaluateValueRecommend evaluateValueRecommend=SpringUtil.getBean(EvaluateValueRecommend.class);
        try {
			evaluateValueRecommend.evaludateValue(recommendQueueVo, topn, trainPt, countPre);
		} catch (TasteException e) {
			logger.error("基于用户计算推荐评分异常:{}",e.getMessage());
			e.printStackTrace();
		}
        logger.info("基于{}相似度{}计算评分end",recommendType,similarity);
    }


}
