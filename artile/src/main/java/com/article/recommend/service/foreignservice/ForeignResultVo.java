package com.article.recommend.service.foreignservice;
/**
 * 
 * @ClassName: ForeignResultVo  
 * @Description: 对外接口  
 * @author sw  
 * @date 2018年4月16日
 */

import java.util.List;

public class ForeignResultVo {
	private String code;
	private String msg;
	private Long userId;
	private List<ArticleResultVo> articleResultVos;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<ArticleResultVo> getArticleResultVos() {
		return articleResultVos;
	}
	public void setArticleResultVos(List<ArticleResultVo> articleResultVos) {
		this.articleResultVos = articleResultVos;
	}
	
}
