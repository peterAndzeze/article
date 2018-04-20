package com.article.recommend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.article.recommend.service.foreignservice.ForeignResultVo;
import com.article.recommend.service.foreignservice.ForeignService;
import com.article.recommend.util.JsonUtil;
/**
 * 
 * @ClassName: ForeignServiceController  
 * @Description: 对外服务  
 * @author sw  
 * @date 2018年4月13日
 */
@RestController
@RequestMapping("foreign")
public class ForeignServiceController {
	@Autowired
	private ForeignService foreignService;
	@RequestMapping("getArticles")
	public String getArticlesByUserId (Long userId) {
		ForeignResultVo foreignResultVo= foreignService.getArticlesById(userId);
		return JsonUtil.objectToJson(foreignResultVo);
	}
}
