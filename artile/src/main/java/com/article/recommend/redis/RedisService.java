package com.article.recommend.redis;


import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
/**
 * 
 * @ClassName: RedisService  
 * @Description: redis 操作  
 * @author sw  
 * @date 2018年4月12日
 */
@Service
public class RedisService{
//	private static JedisPool jedisPool=SpringUtil.getBean(JedisPool.class);
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	/**
	 * 
	 * @Title: putMapList  
	 * @Description: TODO  
	 * @param key
	 * @param map
	 * @param expire
	 * @param timeUnit 时间类型（天，小时，分钟...）
	 * @return void    
	 * @author sw
	 * @throws
	 */
	public  void putMap(String key,Map<?, ?> map,Long expire,TimeUnit timeUnit) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			if (expire > 0) {
				redisTemplate.expire(key, expire, TimeUnit.HOURS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取
	public  Map<Object, Object> getMap(String key) {
		try {
			return redisTemplate.opsForHash().entries(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @Title: putList  
	 * @Description: 存入list  
	 * @param key
	 * @param list
	 * @param expire       
	 * @return void    
	 * @author sw
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public   void putList(String key,List list,Long expire,TimeUnit timeUnit) {
		try {
			redisTemplate.opsForList().rightPushAll(key, list);
			if(expire>0) {
				redisTemplate.expire(key, expire, timeUnit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Title: getList  
	 * @Description: 取  
	 * @param key
	 * @param start
	 * @param end       
	 * @return void    
	 * @author sw
	 * @throws
	 */
	public List<Object> getList(String key,int start,int end) {
		try {
			return redisTemplate.opsForList().range(key, start, end);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @Title: deleteByKey  
	 * @Description: 删除  
	 * @param key       
	 * @return void    
	 * @author sw
	 * @throws
	 */
	public void deleteByKey(String key) {
		redisTemplate.delete(key);
	}
	
}
