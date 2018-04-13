package com.article.recommend.service.foreignservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
/**
 * 
 * @ClassName: ForeignService  
 * @Description: 对外业务服务  
 * @author sw  
 * @date 2018年4月13日
 */
import org.springframework.transaction.annotation.Transactional;

import com.article.recommend.redis.RedisService;
@Service
public class ForeignService {
	@Autowired
	private RedisService redisService;
	/**
	 * 
	 * @Title: getArticlesById  
	 * @Description: 用户  
	 * @param userId
	 * @return       
	 * @return String    
	 * @author sw
	 * @throws
	 */
	@Transactional(value="",propagation=Propagation.REQUIRED)
	public String getArticlesById(String userId) {
		List<ArticleResultVo> articleResultVos=redisService.getList("user"+, start, end)
	}
}
