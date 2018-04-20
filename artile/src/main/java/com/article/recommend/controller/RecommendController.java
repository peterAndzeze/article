package com.article.recommend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.article.recommend.constant.PageModel;
import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.framework.springmvc.BaseController;
import com.article.recommend.redis.RedisService;
@RequestMapping("recresult")
@Controller
public class RecommendController extends BaseController{
	@Autowired
	private RedisService redisService;
	@RequestMapping("page")
	@ResponseBody
	public String page(PageModel pageModel,Long userId) {
		String key=RecommendConstant.USER_REDIS+userId;
		int start=(pageModel.getStart()/pageModel.getLimit())+1;
		int limit=pageModel.getLimit();
		pageModel.setLimit(start*limit-1);
		pageModel.setStart((start-1)*limit);
		List<Object> datas=redisService.getList(key, pageModel.getStart(),pageModel.getLimit());
		Long count=redisService.getCount(key);
		pageModel.setRowCount(count.intValue());
		pageModel.setRecords(datas);
		return jsonStrData(pageModel);
	}
	
	@RequestMapping("main")
	@Override
	public String getPath(HttpServletRequest request) {
		return "recommend/main";
	}

}
