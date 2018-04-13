package com.article.recommend.recommend.baseiterm;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.recommend.RecommendFactory;
import com.article.recommend.recommend.RecommendQueueVo;

import java.io.IOException;
import java.util.List;

public class ItermRecommender {
    /**
     * 文章欧式距离
     * @param dataModel
     * @param nearNum
     * @return
     * @throws TasteException
     * @throws IOException
     */
    public static Recommender itemEuclidean(DataModel dataModel,List<RecommendQueueVo> recommendQueueVos) throws TasteException, IOException {
        ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.EUCLIDEAN, dataModel);
        RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, true);
        recommendQueueVos.add(createQueu(recommenderBuilder, dataModel, RecommendConstant.RECOMMEND_TYPE_BASEITERM+"_"+RecommendFactory.SIMILARITY.EUCLIDEAN.name()));	
        Recommender recommender=RecommendFactory.itemRecommender(dataModel,itemSimilarity,true);
        return recommender;
    }

    /**
     * 文章余弦
     * @param dataModel
     * @param nearNum
     * @return
     * @throws TasteException
     * @throws IOException
     */
    public static Recommender itemCosin(DataModel dataModel,List<RecommendQueueVo> recommendQueueVos) throws TasteException, IOException {
    	ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.COSINE, dataModel);
        RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, true);
        recommendQueueVos.add(createQueu(recommenderBuilder, dataModel, RecommendConstant.RECOMMEND_TYPE_BASEITERM+"_"+RecommendFactory.SIMILARITY.COSINE.name()));	
        Recommender recommender=RecommendFactory.itemRecommender(dataModel,itemSimilarity,true);
        return recommender;
    }

    /**
     * 文章皮而逊
     * @param dataModel
     * @param nearNum
     * @return
     * @throws TasteException
     * @throws IOException
     */
    public static Recommender itemPearson(DataModel dataModel,List<RecommendQueueVo> recommendQueueVos) throws TasteException, IOException {
    	ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.PEARSON, dataModel);
        RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, true);
        recommendQueueVos.add(createQueu(recommenderBuilder, dataModel, RecommendConstant.RECOMMEND_TYPE_BASEITERM+"_"+RecommendFactory.SIMILARITY.PEARSON.name()));
        Recommender recommender=RecommendFactory.itemRecommender(dataModel,itemSimilarity,true);
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
    public static Recommender itemTanimoto(DataModel dataModel,List<RecommendQueueVo> recommendQueueVos) throws TasteException, IOException {
    	ItemSimilarity itemSimilarity =RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.TANIMOTO, dataModel);
        RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, true);
        recommendQueueVos.add(createQueu(recommenderBuilder, dataModel, RecommendConstant.RECOMMEND_TYPE_BASEITERM+"_"+RecommendFactory.SIMILARITY.TANIMOTO.name()));
        Recommender recommender=RecommendFactory.itemRecommender(dataModel,itemSimilarity,true);
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
    public static Recommender itemCityblock(DataModel dataModel,List<RecommendQueueVo> recommendQueueVos) throws TasteException, IOException {
    	ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.CITYBLOCK, dataModel);
        RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, true);
        recommendQueueVos.add(createQueu(recommenderBuilder, dataModel, RecommendConstant.RECOMMEND_TYPE_BASEUSER+"_"+RecommendFactory.SIMILARITY.CITYBLOCK.name()));
        Recommender recommender=RecommendFactory.itemRecommender(dataModel,itemSimilarity,true);
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
    public static Recommender itemLoglikelihood(DataModel dataModel,List<RecommendQueueVo> recommendQueueVos) throws TasteException, IOException {
    	ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.LOGLIKELIHOOD, dataModel);
        RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, true);
        recommendQueueVos.add(createQueu(recommenderBuilder, dataModel, RecommendConstant.RECOMMEND_TYPE_BASEUSER+"_"+RecommendFactory.SIMILARITY.LOGLIKELIHOOD.name()));
        Recommender recommender=RecommendFactory.itemRecommender(dataModel,itemSimilarity,true);
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
    	queueVo.setRecommendType(RecommendConstant.RECOMMEND_TYPE_BASEITERM);
    	queueVo.setSimilarity(similarity);
    	return queueVo;
    }

}
