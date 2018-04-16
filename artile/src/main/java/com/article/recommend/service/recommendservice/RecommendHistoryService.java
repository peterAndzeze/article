package com.article.recommend.service.recommendservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.article.recommend.mapper.localMapper.RecommendHistoryMapper;
import com.article.recommend.service.foreignservice.ArticleResultVo;

import org.springframework.stereotype.Service;
/**
 * 
 * @ClassName: RecommendHistoryService  
 * @Description: 推荐历史  
 * @author sw  
 * @date 2018年4月16日
 */
@Service
public class RecommendHistoryService {
	@Autowired
	private RecommendHistoryMapper recommendHistoryMapper;
	/**
	 * 
	 * @Title: getArtilesByUserId  
	 * @Description: 获取文章ids
	 * @param userId
	 * @return       
	 * @return List<Long>    
	 * @author sw
	 * @throws
	 */
	public List<Long> getArtilesByUserId(Long userId){
		return recommendHistoryMapper.getArtilesByUserId(userId);
	}
	/**
	 * 插入
	 * @Title: insertRecommendHistory  
	 * @Description: 插入操作历史
	 * @param userId
	 * @param articleResultVos       
	 * @return void    
	 * @author sw
	 * @throws
	 */
	public void insertRecommendHistory (Long userId,List<ArticleResultVo> articleResultVos) {
		recommendHistoryMapper.insertRecommendHistory(userId, articleResultVos);
	}
	
}
