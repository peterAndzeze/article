package com.article.recommend.mapper.localMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.article.recommend.constant.PageModel;
import com.article.recommend.entity.RecommendHistoryInfo;
import com.article.recommend.service.foreignservice.ArticleResultVo;
/**
 * 
 * @ClassName: RecommendHistoryMapper  
 * @Description: 推荐历史  
 * @author sw  
 * @date 2018年4月16日
 */
@Mapper
public interface RecommendHistoryMapper {
	/**
	 * 
	 * @Title: getArtilesByUserId  
	 * @Description: 根据用户id 获取推荐过的历史  
	 * @param userId
	 * @return       
	 * @return List<Long>    
	 * @author sw
	 * @throws
	 */
	public List<Long> getArtilesByUserId(Long userId);
	/**
	 * 
	 * @Title: insertRecommendHistory  
	 * @Description:   
	 * @param articleResultVos       
	 * @return void    
	 * @author sw
	 * @throws
	 */
	public void insertRecommendHistory(@Param("userId")Long userId,@Param("articleResultVos")List<ArticleResultVo> articleResultVos) ;
	/**
	 * 
	 * @Title: count  
	 * @Description: 总数         
	 * @author sw
	 * @param userId
	 * @return
	 */
	public int count(@Param("userId")Long userId);
	/**
	 * 分页数据
	 * @Title: page  
	 * @Description: TODO         
	 * @author sw
	 * @param historyInfo
	 * @param pageModel
	 * @return
	 */
	public List<RecommendHistoryInfo> page(@Param("history")RecommendHistoryInfo historyInfo,@Param("page")PageModel pageModel);
	
}
