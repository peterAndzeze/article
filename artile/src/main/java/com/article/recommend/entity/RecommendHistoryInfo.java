package com.article.recommend.entity;
/**
 * 
 * @ClassName: RecommendHistoryInfo  
 * @Description: 推荐历史  
 * @author sw  
 * @date 2018年4月16日
 */
public class RecommendHistoryInfo {
	private Long id;
	private Long userId;
	private Long articleId;
	private Float value;
	private String createTime;
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	
}
