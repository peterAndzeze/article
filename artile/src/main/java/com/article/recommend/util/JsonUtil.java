package com.article.recommend.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @ClassName: JsonUtil
 * @Description: 简易json操作
 * @author sw
 * @date 2018年4月17日
 */
public class JsonUtil {
	/**
	 * 
	 * @Title: objectToJson
	 * @Description: object 转json
	 * @author sw
	 * @param value
	 * @return
	 */
	public static String objectToJson(Object value) {
		if (null == value) {
			return "";
		}
		return JSON.toJSONString(value);
	}

	/**
	 * 
	 * @Title: objectToJsonExcludeProperty
	 * @Description: 排除某个属性
	 * @author sw
	 * @param object
	 * @param properties
	 * @return
	 */
	public static String objectToJsonExcludeProperty(Object object, String... properties) {
		JSONObject jsonObject = objectToJSONObject(object);
		if (null != jsonObject) {
			if (null != properties) {
				for (String property : properties) {
					jsonObject.remove(property);
				}
			}
		}
		return jsonObject.toString();
	}

	/**
	 * 
	 * @Title: objectToJSONObject  
	 * @Description: 返回jsonObject         
	 * @author sw
	 * @param object
	 * @return
	 */
	public static JSONObject objectToJSONObject(Object object) {
		if (null != object) {
			String jsonStr = objectToJson(object);
			return JSONObject.parseObject(jsonStr);
		}
		return null;
	}

	/**
	 * 
	 * @Title: objectToJSONOArr  
	 * @Description: 转json数组         
	 * @author sw
	 * @param object
	 * @return
	 */
	public static JSONArray objectToJSONOArr(Object object) {
		if (null != object) {
			String jsonStr = objectToJson(object);
			return JSONArray.parseArray(jsonStr);
		}
		return null;
	}

	/**
	 * 
	 * @Title: jsonToObject  
	 * @Description: 转对象         
	 * @author sw
	 * @param jsonStr
	 * @param className
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object jsonToObject(String jsonStr, Class className) {
		if (null != jsonStr) {
			return JSON.parseObject(jsonStr, className);
		}
		return null;
	}

	/**
	 * 
	 * @Title: jsonToList  
	 * @Description: 转list         
	 * @author sw
	 * @param jsonStr
	 * @param className
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object jsonToList(String jsonStr, Class className) {
		if (null != jsonStr) {
			return JSONArray.parseArray(jsonStr, className);
		}
		return null;
	}

	/**
	 * 
	 * @Title: jsonToMapObject
	 * @Description:json 转为map<String,javaBean>
	 * @author sw
	 * @param jsonStr
	 * @param className
	 *            map.class
	 * @param javaBean
	 *            javeBean.class
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object jsonToMapObject(String jsonStr, Class className, Class javaBean) {
		if (null != jsonStr) {
			JSONObject jsonObject = JSONObject.parseObject(jsonStr);
			Map<String, Object> map = (Map<String, Object>) JSONObject.toJavaObject(jsonObject, className);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if (null != entry.getValue()) {
					Object object = jsonToObject(objectToJson(entry.getValue()), javaBean);
					map.put(entry.getKey(), object);
				}
			}
			return map;
		}
		return null;
	}

	/**
	 * 
	 * @Title: jsonToMapList
	 * @Description:
	 * @author sw
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object jsonToMapList(String jsonStr, Class className, Class javaBean) {
		if (null != jsonStr) {
			JSONObject jsonObject = JSONObject.parseObject(jsonStr);
			Map<String, Object> map = (Map<String, Object>) JSONObject.toJavaObject(jsonObject, className);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if (null != entry.getValue()) {
					List<Object> object = (List<Object>) jsonToList(objectToJson(entry.getValue()), javaBean);
					map.put(entry.getKey(), object);
				}
			}
			return map;
		}
		return null;
	}

}
