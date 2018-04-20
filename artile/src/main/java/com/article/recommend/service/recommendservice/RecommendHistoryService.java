package com.article.recommend.service.recommendservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.article.recommend.constant.PageModel;
import com.article.recommend.entity.RecommendHistoryInfo;
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
	/**
	 * 分页数据
	 * @Title: page  
	 * @Description: TODO         
	 * @author sw
	 * @param pageModel
	 * @param recommendHistoryInfo
	 * @return
	 */
	public PageModel page(PageModel pageModel,RecommendHistoryInfo recommendHistoryInfo) {
		int count=recommendHistoryMapper.count(1L);
		pageModel.setRowCount(count);
		List<RecommendHistoryInfo> historyInfos=recommendHistoryMapper.page(recommendHistoryInfo, pageModel);
		pageModel.setRecords(historyInfos);
		return pageModel;
	}
	
	
}
