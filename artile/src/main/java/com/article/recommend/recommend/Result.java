package com.article.recommend.recommend;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
/**
 * 
 * @ClassName: Result  
 * @Description: 推荐结果，用于后续业务处理  
 * @author sw  
 * @date 2018年4月12日
 */
public class Result implements RecommendedItem {
	@SuppressWarnings("unused")
	private long itemID;
	@SuppressWarnings("unused")
	private float value;
	
	public void setItemID(long itemID) {
		this.itemID = itemID;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public long getItemID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
