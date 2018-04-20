package com.article.recommend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.article.recommend.constant.PageModel;
import com.article.recommend.entity.RecommendHistoryInfo;
import com.article.recommend.framework.springmvc.BaseController;
import com.article.recommend.service.recommendservice.RecommendHistoryService;
@RequestMapping("rechistory")
@Controller
public class RechistoryController extends BaseController {
	@Autowired
	private RecommendHistoryService recommendHistoryService;
	@RequestMapping("page")
	@ResponseBody
	public String page(PageModel pageModel,RecommendHistoryInfo recommendHistoryInfo) {
		pageModel=recommendHistoryService.page(pageModel, recommendHistoryInfo);
		return jsonStrData(pageModel);
	}
	
	@RequestMapping("main")
	@Override
	public String getPath(HttpServletRequest request) {
		return "rechistory/main";
	}

}
