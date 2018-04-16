package com.article.recommend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * @ClassName: ForeignServiceController  
 * @Description: 对外服务  
 * @author sw  
 * @date 2018年4月13日
 */
@RestController
@RequestMapping("/recommend")
public class ForeignServiceController {
	@RequestMapping("getArticles")
	public String getArticlesByUserId (String userId) {
		return null;
	}
}
