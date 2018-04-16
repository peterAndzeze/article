package com.article.recommend.recommend;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.article.recommend.ArticlerecommendApplicationTests;
import com.article.recommend.service.foreignservice.ForeignResultVo;
import com.article.recommend.service.foreignservice.ForeignService;

public class RecommendTest extends ArticlerecommendApplicationTests{
	@Autowired
	private ForeignService foreignService;
	@Test
	public void recommend() {
		ForeignResultVo foreignResultVo=   foreignService.getArticlesById(1L);
		System.out.println(JSON.toJSONString(foreignResultVo, SerializerFeature.WRITE_MAP_NULL_FEATURES));
	}
}
