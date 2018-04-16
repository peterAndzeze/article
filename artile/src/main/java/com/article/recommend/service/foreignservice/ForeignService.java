package com.article.recommend.service.foreignservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.redis.RedisService;
import com.article.recommend.service.recommendservice.RecommendHistoryService;
/**
 * 
 * @ClassName: ForeignService  
 * @Description: 对外业务服务  
 * @author sw  
 * @date 2018年4月13日
 */
@Service
public class ForeignService {
	private static Logger logger=LoggerFactory.getLogger(ForeignService.class);
	@Autowired
	private RedisService redisService;
	@Autowired
	private RecommendHistoryService recommendHistoryService;
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
	@Transactional(value="localDataTransactionManager",propagation=Propagation.REQUIRED)
	public ForeignResultVo getArticlesById(Long userId) {
		//从redis中取出
		logger.info("用户{}取数据 start",userId);
		List<Object> resultMaps =redisService.getList("user:"+userId, 0, -1);
		if(null == resultMaps || resultMaps.isEmpty()) {
			logger.info("用户{}没有推荐数据",userId);
			return createForeignResult(ForeignRedultEnum.FOREIGN_NODATA_CODE, userId, null);
		}
		logger.info("用户{}取出数据，并进行业务处理 start",userId);
		String jsonMap = JSONArray.toJSONString(resultMaps);
		logger.info("用户{}将数据转为json  end", userId);
		List<ArticleResultVo> articleResultVos = JSONArray.parseArray(jsonMap, ArticleResultVo.class);
		logger.info("用户{}将数据转为实体集合  end", userId);
		Collections.sort(articleResultVos, new Comparator<ArticleResultVo>() {
			public int compare(ArticleResultVo a1, ArticleResultVo a2) {
				return a2.getValue().compareTo(a1.getValue());
			}
		});
		logger.info("用户{}推荐数据排序  end", userId);
		List<Long> ids = new ArrayList<>(articleResultVos.size());//用来临时存储iterm的id
		List<ArticleResultVo> newResult = articleResultVos.stream().filter(v -> {
			boolean flag = !ids.contains(v.getItemID());
			ids.add(v.getItemID());
			return flag;
		}).collect(Collectors.toList());
		logger.info("用户{}推荐数据过滤重复  end", userId);
		//过滤推荐历史
		List<Long> historys=recommendHistoryService.getArtilesByUserId(userId);
		List<ArticleResultVo> newList=new ArrayList<>();
		if(null!=historys && !historys.isEmpty()) {//
			//过滤历史
			newList=newResult.stream().filter(item -> !historys.contains(item.getItemID())).collect(Collectors.toList());
		}else {
			newList=newResult;
		}
		logger.info("用户{}推荐数据过滤历史  end", userId);

		//插入推荐历史，并村redis中删除
		recommendHistoryService.insertRecommendHistory(userId, newList);
		redisService.deleteByKey("user:"+userId);
		return createForeignResult(ForeignRedultEnum.FOREIGN_SUCCESS_CODE, userId, newResult);
	}
	/**
	 * 
	 * @Title: createForeignResult  
	 * @Description: 组装返回  
	 * @param code
	 * @param msg
	 * @param userId
	 * @param articleResultVos
	 * @return       
	 * @return ForeignResultVo    
	 * @author sw
	 * @throws
	 */
	protected   ForeignResultVo createForeignResult(ForeignRedultEnum foreignRedultEnum,Long userId,List<ArticleResultVo> articleResultVos) {
		ForeignResultVo foreignResultVo =new ForeignResultVo();
		foreignResultVo.setCode(foreignRedultEnum.getCode());
		foreignResultVo.setMsg(foreignRedultEnum.getMsg());
		foreignResultVo.setUserId(userId);
		foreignResultVo.setArticleResultVos(articleResultVos);
		return foreignResultVo;
	}
	
	public static void main(String[] args) {
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
		List<Long> aList1=Arrays.asList(1001L);	
		List<ArticleResultVo> newList=aList.stream().filter(item -> !aList1.contains(item.getItemID())).collect(Collectors.toList());
        newList.forEach(e -> System.out.println(e.getItemID()+"--->"+e.getValue()));
        
        List<Integer> list1=new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2=new ArrayList<>();
        list2.add(3);

        System.out.println("====求交集===");

        List<Integer> list=list1.stream().filter(t->!list2.contains(t)).collect(Collectors.toList());
        System.out.println(list.isEmpty());

        
	}
	
	
	
	
}
