package com.article.recommend.recommend.baseuser;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.recommend.RecommendFactory;
import com.article.recommend.recommend.RecommendQueueVo;

import java.io.IOException;
import java.util.List;

public class UserRecommender {
    /**
     * 用户欧式距离
     * @param dataModel
     * @param nearNum
     * @return
     * @throws TasteException
     * @throws IOException
     */
    public static Recommender userEuclidean(DataModel dataModel, int nearNum,List<RecommendQueueVo> recommendQueueVos) throws TasteException, IOException {
        UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.EUCLIDEAN, dataModel);
        UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST, userSimilarity, dataModel, nearNum);
        RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood, true);
        recommendQueueVos.add(createQueu(recommenderBuilder, dataModel, RecommendConstant.RECOMMEND_TYPE_BASEUSER+"_"+RecommendFactory.SIMILARITY.EUCLIDEAN.name()));	
        Recommender recommender=RecommendFactory.userRecommender(dataModel,userSimilarity,userNeighborhood,true);
        return recommender;
    }

    /**
     * 用户余弦
     * @param dataModel
     * @param nearNum
     * @return
     * @throws TasteException
     * @throws IOException
     */
    public static Recommender userCosin(DataModel dataModel, int nearNum,List<RecommendQueueVo> recommendQueueVos) throws TasteException, IOException {
        UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.COSINE, dataModel);
        UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST, userSimilarity, dataModel, nearNum);
        RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood, true);
        recommendQueueVos.add(createQueu(recommenderBuilder, dataModel, RecommendConstant.RECOMMEND_TYPE_BASEUSER+"_"+RecommendFactory.SIMILARITY.COSINE.name()));	
        Recommender recommender=RecommendFactory.userRecommender(dataModel,userSimilarity,userNeighborhood,true);
        return recommender;
    }

    /**
     * 用户皮而逊
     * @param dataModel
     * @param nearNum
     * @return
     * @throws TasteException
     * @throws IOException
     */
    public static Recommender userPearson(DataModel dataModel, int nearNum,List<RecommendQueueVo> recommendQueueVos) throws TasteException, IOException {
        UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.PEARSON, dataModel);
        UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST, userSimilarity, dataModel, nearNum);
        RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood, true);
        recommendQueueVos.add(createQueu(recommenderBuilder, dataModel, RecommendConstant.RECOMMEND_TYPE_BASEUSER+"_"+RecommendFactory.SIMILARITY.PEARSON.name()));
        Recommender recommender=RecommendFactory.userRecommender(dataModel,userSimilarity,userNeighborhood,true);
        return recommender;
    }

    /**
     *
     * @param dataModel
     * @param nearNum
     * @return
     * @throws TasteException
     * @throws IOException
     */
    public static Recommender userTanimoto(DataModel dataModel, int nearNum,List<RecommendQueueVo> recommendQueueVos) throws TasteException, IOException {
        UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.TANIMOTO, dataModel);
        UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST, userSimilarity, dataModel, nearNum);
        RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood, true);
        recommendQueueVos.add(createQueu(recommenderBuilder, dataModel, RecommendConstant.RECOMMEND_TYPE_BASEUSER+"_"+RecommendFactory.SIMILARITY.TANIMOTO.name()));
       
        Recommender recommender=RecommendFactory.userRecommender(dataModel,userSimilarity,userNeighborhood,true);
        return recommender;
    }

    /**
     *
     * @param dataModel
     * @param nearNum
     * @return
     * @throws TasteException
     * @throws IOException
     */
    public static Recommender userCityblock(DataModel dataModel, int nearNum,List<RecommendQueueVo> recommendQueueVos) throws TasteException, IOException {
        UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.CITYBLOCK, dataModel);
        UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST, userSimilarity, dataModel, nearNum);
        RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood, true);
        recommendQueueVos.add(createQueu(recommenderBuilder, dataModel, RecommendConstant.RECOMMEND_TYPE_BASEUSER+"_"+RecommendFactory.SIMILARITY.CITYBLOCK.name()));
       
        Recommender recommender=RecommendFactory.userRecommender(dataModel,userSimilarity,userNeighborhood,true);
        return recommender;
    }

    /**
     *
     * @param dataModel
     * @param nearNum
     * @return
     * @throws TasteException
     * @throws IOException
     */
    public static Recommender userLoglikelihood(DataModel dataModel, int nearNum,List<RecommendQueueVo> recommendQueueVos) throws TasteException, IOException {
        UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.LOGLIKELIHOOD, dataModel);
        UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST, userSimilarity, dataModel, nearNum);
        RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood, true);
        recommendQueueVos.add(createQueu(recommenderBuilder, dataModel, RecommendConstant.RECOMMEND_TYPE_BASEUSER+"_"+RecommendFactory.SIMILARITY.LOGLIKELIHOOD.name()));
       
        Recommender recommender=RecommendFactory.userRecommender(dataModel,userSimilarity,userNeighborhood,true);
        return recommender;
    }
    /**
     * 
    * @Title: sendQueu  
    * @Description: 组装队列vo  
    * @param recommenderBuilder
    * @param dataModel
    * @param similarity  相似度类型     
    * @return RecommendQueueVo    
    * @author sw
    * @throws
     */
    private static RecommendQueueVo createQueu(RecommenderBuilder recommenderBuilder,DataModel dataModel,String similarity) {
    	RecommendQueueVo queueVo = new RecommendQueueVo();
    	queueVo.setRecommenderBuilder(recommenderBuilder);
    	queueVo.setDataModel(dataModel);
    	queueVo.setRecommendType(RecommendConstant.RECOMMEND_TYPE_BASEUSER);
    	queueVo.setSimilarity(similarity);
    	return queueVo;
    }

}
