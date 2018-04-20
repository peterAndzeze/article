package com.article.recommend.recommend;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.article.recommend.ArticlerecommendApplicationTests;
import com.article.recommend.constant.PageModel;
import com.article.recommend.entity.RecommendHistoryInfo;
import com.article.recommend.mapper.localMapper.RecommendHistoryMapper;
import com.article.recommend.service.foreignservice.ArticleResultVo;

public class RecommendHistoryTest extends ArticlerecommendApplicationTests {
	@Autowired
	private RecommendHistoryMapper recommendHistoryMapper;
	@Test
	public void getArticlesByUserId() {
		List<Long> articles=recommendHistoryMapper.getArtilesByUserId(1L);
		articles.forEach(System.out::println);
	}
	
	@Test
	public void insertHistory() {
		ArticleResultVo articleResultVo=new ArticleResultVo();
		articleResultVo.setItemID(1001L);
		articleResultVo.setValue(1.9f);
		ArticleResultVo articleResultVo1=new ArticleResultVo();
		articleResultVo1.setItemID(1002L);
		articleResultVo1.setValue(1.8f);
		ArticleResultVo articleResultVo3=new ArticleResultVo();
		articleResultVo3.setItemID(1003L);
		articleResultVo3.setValue(1.8f);
		List<ArticleResultVo> aList=Arrays.asList(articleResultVo,articleResultVo1,articleResultVo3);
		recommendHistoryMapper.insertRecommendHistory(1L, aList);
	}
	
	@Test
	public void page() {
		int count=recommendHistoryMapper.count(1L);
		System.out.println(count+"*************");
		
		PageModel pageModel=new PageModel();
		pageModel.setStart(0);
		pageModel.setLimit(2);
		RecommendHistoryInfo recommendHistoryInfo=new RecommendHistoryInfo();
		recommendHistoryInfo.setUserId(1L);
		List<RecommendHistoryInfo> historyInfos=recommendHistoryMapper.page(recommendHistoryInfo, pageModel);
		System.out.println(JSONArray.toJSONString(historyInfos));
		
	}
	
}
