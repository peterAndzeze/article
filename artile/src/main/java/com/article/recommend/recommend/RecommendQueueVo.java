package com.article.recommend.recommend;
/**
 * 
* @ClassName: RecommendQueueVo  
* @Description: 队列中的属性  
* @author sw  
* @date 2018年4月10日
 */

import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.model.DataModel;
/**
 * 
* @ClassName: RecommendQueueVo  
* @Description: 队列vo类用户传递数据对象  
* @author sw  
* @date 2018年4月10日
 */
public class RecommendQueueVo {
	/**推荐类型*/
	private String recommendType;
	/**相似度类型**/
	public String similarity;
	/***推荐builder*/
	public RecommenderBuilder recommenderBuilder;
	/**数据集***/
	public DataModel dataModel;
	public String getRecommendType() {
		return recommendType;
	}
	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
	}
	public String getSimilarity() {
		return similarity;
	}
	public void setSimilarity(String similarity) {
		this.similarity = similarity;
	}
	public RecommenderBuilder getRecommenderBuilder() {
		return recommenderBuilder;
	}
	public void setRecommenderBuilder(RecommenderBuilder recommenderBuilder) {
		this.recommenderBuilder = recommenderBuilder;
	}
	public DataModel getDataModel() {
		return dataModel;
	}
	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}
	
}
