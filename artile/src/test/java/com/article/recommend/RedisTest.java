package com.article.recommend;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.alibaba.fastjson.JSONArray;
import com.article.recommend.redis.RedisService;
import com.article.recommend.service.foreignservice.ArticleResultVo;
import com.article.recommend.vo.PersonVo;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	@Autowired
	private RedisService redisService;
	
 	@Test
	public void getConfig() {
//		String name=RedisService.getStringValue("name");
//		System.out.println(name+"******************");
	}
	@Test
	public void redisMap() {
		Map<Long, List<PersonVo>> maps=new HashMap<>();
		Map<String, String> redisMap=new HashMap<>();
		for (Long i = 0L; i < 2; i++) {
			PersonVo personVo = new PersonVo(i, Float.parseFloat("2.0"));
			/*
			 * list.add(personVo); list.add(personVo1);
			 */
			if (maps.containsKey(i)) {
				maps.get(i).add(personVo);
			}else {
				List<PersonVo> list=Arrays.asList(personVo);
				maps.put(i, list);
			}
		}
		//redis 存储
		System.out.println(LocalDateTime.now());
		for(Entry<Long, List<PersonVo>> entry:maps.entrySet()) {
			Map<Long, Float> maps1=entry.getValue().stream().collect(Collectors.toMap(PersonVo::getId,PersonVo::getValue,(getId,getValue)->getValue));
			redisService.putMap(String.valueOf(entry.getKey()),maps1,0L,TimeUnit.DAYS);
			 Map<Object, Object> getValues=redisService.getMap(String.valueOf(entry.getKey()));
			 System.out.println("取出的出数据:"+getValues.toString());
		}
		System.out.println(LocalDateTime.now());
		//取出集合
		//RedisService.putMap(RecommendConstant.REDIS_USER_RECOMMEND_KEY, redisMap);
		
		
	}
	@Test
	public void testList() {
	RecommendedItem recommendedItem=new RecommendedItem() {
			
			@Override
			public float getValue() {
				return 0;
			}
			
			@Override
			public long getItemID() {
				return 1001121;
			}
		};
		RecommendedItem recommendedItem1=new RecommendedItem() {
			@Override
			public float getValue() {
				return 0.1f;
			}
			
			@Override
			public long getItemID() {
				return 10014;
			}
		};
		List<RecommendedItem> recommendedItems=Arrays.asList(recommendedItem,recommendedItem1);
		
		redisService.putList("userId:1", recommendedItems, 0L, TimeUnit.DAYS);
		
//		List<Object> dataVos= redisService.getList("user:0", 0, -1);
////		System.out.println("获取0:"+JSONArray.toJSONString(dataVos));
//		List<Long> ids = new ArrayList<>(dataVos.size());//用来临时存储iterm的id
//		String rString= JSONArray.toJSONString(dataVos);
//		System.out.println(rString);
//        List<ArticleResultVo> userList = JSONArray.parseArray(rString, ArticleResultVo.class);  
//        Collections.sort(userList, new Comparator<ArticleResultVo>() {
//        	public int compare(ArticleResultVo a1, ArticleResultVo a2) {
//                return a2.getValue().compareTo(a1.getValue());
//            }
//        });
//		List<ArticleResultVo> newResult=userList.stream().filter(v->{
//			boolean flag=!ids.contains(v.getItemID());
//			ids.add(v.getItemID());
//			return flag;
//		}).collect(Collectors.toList());
//		System.out.println(JSONArray.toJSONString(newResult));
//		
	
//		System.out.println(LocalDateTime.now());
//		List<DataVo> dataVos=(List<DataVo>) redisService.getList("userId:1", 0, -1);
//		System.out.println(dataVos.size()+"**********************");
//		List<Integer> ids = new ArrayList<>(dataVos.size());//用来临时存储iterm的id
//        //并行计算去重复
//		try {
//			List<DataVo> newResult=dataVos.parallelStream().filter(v->{
//				boolean flag=!ids.contains(v.getCount());
//				ids.add(v.getCount());
//				return flag;
//			}).collect(Collectors.toList());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(LocalDateTime.now());
//		
	}
	
	
	public static void main(String[] args) {
	/*	List<PersonVo> list=new ArrayList<>();
		for (Long i = 0L; i < 2; i++) {
			PersonVo personVo = new PersonVo(i, Float.parseFloat("2.0"));
			list.add(personVo);
		}
		for (Long i = 0L; i < 2; i++) {
			PersonVo personVo = new PersonVo(i, Float.parseFloat("2.0"));
			list.add(personVo);
		}
		RecommendedItem recommendedItem=new RecommendedItem() {
			
			@Override
			public float getValue() {
				return 0;
			}
			
			@Override
			public long getItemID() {
				return 0;
			}
		};
		RecommendedItem recommendedItem1=new RecommendedItem() {
			@Override
			public float getValue() {
				return 0.1f;
			}
			
			@Override
			public long getItemID() {
				return 0;
			}
		};
		List<RecommendedItem> recommendedItems=Arrays.asList(recommendedItem,recommendedItem1);
//		Map<Long, Float> maps=recommendedItems.parallelStream().collect(Collectors.toMap(RecommendedItem::getItemID,RecommendedItem::getValue));
//		System.out.println(maps);
		
		Map<Long, Float> maps1=recommendedItems.stream().collect(Collectors.toMap(RecommendedItem::getItemID,RecommendedItem::getValue,(getItemID,getValue)->getValue));
		System.out.println(maps1);
		byte [] bytes=SerializeUtil.serialize(list);
		System.out.println("序列化:"+bytes);
		List<PersonVo> v=SerializeUtil.unserializeForList(bytes);
		System.out.println(JSONArray.toJSON(v));*/
    	List<PersonVo> data =new ArrayList<>();
    	System.out.println(0%2);
    	PersonVo dataVo=null;
	        for (int i=0;i<4;i++) {
	        	if(i%2==0) {
		        	dataVo=new PersonVo(1L,0.2f);
	        	}else {
		        	dataVo=new PersonVo(2L,0.3f);
	        	}
	           data.add(dataVo);
	        }
	        System.out.println(data.size());
	        List<Long> ids=new ArrayList<>();
	        List<PersonVo> newResult=data.stream().filter(v->{
				boolean flag=!ids.contains(v.getId());
				ids.add(v.getId());
				return flag;
			}).collect(Collectors.toList());

	    	   System.out.println(JSONArray.toJSONString(data));
	    	   System.out.println(JSONArray.toJSONString(newResult));
		
	}
	@Test
	public void test() {
		List<PersonVo> data =new ArrayList<>();
    	System.out.println(0%2);
    	PersonVo dataVo=null;
	        for (int i=0;i<4;i++) {
	        	if(i%2==0) {
		        	dataVo=new PersonVo(1L,0.2f);
	        	}else {
		        	dataVo=new PersonVo(2L,0.3f);
	        	}
	           data.add(dataVo);
	        }
	        System.out.println(data.size());
	        List<Long> ids=new ArrayList<>();
	        List<PersonVo> newResult=data.stream().filter(v->{
				boolean flag=!ids.contains(v.getId());
				ids.add(v.getId());
				return flag;
			}).collect(Collectors.toList());

	    	   System.out.println(JSONArray.toJSONString(data));
	    	   System.out.println(JSONArray.toJSONString(newResult));
		
	}
	
	
	
	
}
