package com.article.recommend.dictionary;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.article.recommend.ArticlerecommendApplicationTests;
import com.article.recommend.constant.PageModel;
import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.entity.DictionaryInfo;
import com.article.recommend.mapper.localMapper.DictionaryMapper;
import com.article.recommend.service.dictionary.DictionaryService;
import com.article.recommend.service.menu.MenuServiceImpl;

public class DictionaryTest extends ArticlerecommendApplicationTests {
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private DictionaryMapper dictionaryMapper;
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	@Test
	public void getValue() {
		  DictionaryInfo countPreInfo=dictionaryService.getDictionaryByKey(RecommendConstant.TOTAL_NUM);
	        //总数比例
	        Double countPre=countPreInfo.getValue()==null?1.0:Double.parseDouble(countPreInfo.getValue());
	        BigDecimal bigDecimal=new BigDecimal(countPreInfo.getValue());
	        bigDecimal=bigDecimal.divide(new BigDecimal(100),6);
	        System.out.println(bigDecimal+",,"+bigDecimal.doubleValue());
	        System.out.println(countPre+"*****"+countPreInfo.getValue());
	}
	@Test
	public void getMenus() {
		String str=menuServiceImpl.nenuTree(0L);
		System.out.println(str);
	}
	@Test
	public void page() {
		PageModel pageModel=new PageModel();
		pageModel.setLimit(1);
		pageModel.setStart(0);
		DictionaryInfo dictionaryInfo=new DictionaryInfo();
//		dictionaryInfo.setKey("article");
		PageModel pageModel2=dictionaryService.getPages(pageModel, dictionaryInfo);
		
		System.out.println("***************count:"+JSON.toJSONString(pageModel2));
		
	}
	
}
