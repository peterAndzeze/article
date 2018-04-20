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
	 * @Title: putMap  
	 * @Description: 存         
	 * @author sw
	 * @param key
	 * @param map
	 * @param expire
	 * @param timeUnit
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
	/**
	 * 
	 * @Title: getMap  
	 * @Description: 获取         
	 * @author sw
	 * @param key
	 * @return
	 */
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
	 * @author sw
	 * @param key
	 * @param list
	 * @param expire
	 * @param timeUnit
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
	 * @author sw
	 * @param key
	 * @param start
	 * @param end
	 * @return
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
	 * @author sw
	 * @param key
	 */
	public void deleteByKey(String key) {
		redisTemplate.delete(key);
	}
	/**
	 * 
	 * @Title: getCount  
	 * @Description: 获取总数         
	 * @author sw
	 * @param key
	 * @return
	 */
	public Long  getCount(String key) {
		return redisTemplate.opsForList().size(key);
	}
	
	
}
