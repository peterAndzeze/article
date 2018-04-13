package com.article.recommend.dictionary;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.article.recommend.ArticlerecommendApplicationTests;
import com.article.recommend.constant.RecommendConstant;
import com.article.recommend.entity.DictionaryInfo;
import com.article.recommend.service.dictionary.DictionaryService;

public class DictionaryTest extends ArticlerecommendApplicationTests {
	@Autowired
	private DictionaryService dictionaryService;
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
}
