package com.article.recommend.recommend;

import com.article.recommend.entity.EvaluateValueInfo;
import com.article.recommend.service.recommendservice.EvaluateService;
import com.article.recommend.util.DateUtil;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
/**
 * 
* @ClassName: EvaluateValueRecommend  
* @Description: 评分计算服务  
* @author sw  
* @date 2018年4月10日
 */
@Service
public class EvaluateValueRecommend {
    private static Logger logger= LoggerFactory.getLogger(EvaluateValueRecommend.class);
    @Autowired
    private EvaluateService evaluateService;
    //指定个数
    public void evaludateValue(RecommendQueueVo recommendQueueVo,int topNum,Double trainPt,Double countPre) throws TasteException {
        //训练数据比例（均方差）//只根据评分
    	double score=Double.NaN;
    	double precision=Double.NaN;
    	double recall=Double.NaN;
    	try {
        	score=RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommendQueueVo.getRecommenderBuilder(), null, recommendQueueVo.getDataModel(), trainPt,countPre);
        	IRStatistics stats= RecommendFactory.statsEvaluator(recommendQueueVo.getRecommenderBuilder(), null, recommendQueueVo.getDataModel(), topNum,countPre);
        	precision=stats.getPrecision();
        	recall=stats.getRecall();
		} catch (Exception e) {
		}
        EvaluateValueInfo evaluateValueInfo=createEvaluateValueInfo(recommendQueueVo.getRecommendType(),recommendQueueVo.getSimilarity(),RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE.name(),score,precision,recall);
        //入库
        evaluateService.insertEvaluatorInfo(evaluateValueInfo);
    }



    /**
     * 创建对象
     * @param cfType 推荐类型（人或物）
     * @param similarityType （相似度计算方式）
     * @param evaluatorType 评分类型
     * @param score 得分
     * @param precision 查准率
     * @param recall 查全率
     * @return 对象
     */
    private EvaluateValueInfo createEvaluateValueInfo(String cfType,String similarityType,String evaluatorType,Double score,Double precision,Double recall){
        EvaluateValueInfo evaluateValueInfo=new EvaluateValueInfo();
        evaluateValueInfo.setCfType(cfType);
        evaluateValueInfo.setSimilarityType(similarityType);
        evaluateValueInfo.setEvaluatorType(evaluatorType);
        if(!precision.isNaN()) {
        	evaluateValueInfo.setPrecision(precision);
        }
        if(!recall.isNaN()) {
        	evaluateValueInfo.setRecall(recall);
        }
        if(!score.isNaN()) {
        	evaluateValueInfo.setScore(score);
        }
        evaluateValueInfo.setCreateTime(DateUtil.dateToString(new Date(),DateUtil.DATETIME));
        return evaluateValueInfo;

    }

    /**
     * 计算最好，score越低，评分越低意味着估计值与实际实际偏好值得差别越小 0.0 完美
     * @param evaluateValueInfos
     * @return
     */
    public  EvaluateValueInfo evaluateMaxValue(List<EvaluateValueInfo> evaluateValueInfos){

        Collections.sort(evaluateValueInfos, new Comparator<EvaluateValueInfo>() {

            public int compare(EvaluateValueInfo h1, EvaluateValueInfo h2) {
                if(h1.getScore().isNaN()){
                    h1.setScore(0.0);
                }
                if (h2.getScore().isNaN()){
                    h2.setScore(0.0);
                }
                return h1.getScore().compareTo(h2.getScore());
            }
        });
        return evaluateValueInfos.get(0);
    }

    public static void main(String[] args) throws TasteException, IOException {

        String  local="data/tmp/tmp.txt";
        DataModel dataModel=RecommendFactory.buildDateModel(local);

        UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.EUCLIDEAN, dataModel);
        UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST, userSimilarity, dataModel, 2);
        RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood, true);

        RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7,0.02);
        RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2,0.02);
        logger.info("基于用户计算EUCLIDEAN end");
    }
}
