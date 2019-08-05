package com.school.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * FastJson工具类
 */
public class FastJsonUtil {
	 /** 记录日志的变量 */
    private static Logger logger = Logger.getLogger(FastJsonUtil.class);
    private static final SerializeConfig config;  
    
    static {  
        config = new SerializeConfig();  
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式  
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式  
        config.put(java.sql.Timestamp.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式  
    }  
  
    private static final SerializerFeature[] features = {
    		SerializerFeature.DisableCircularReferenceDetect,// 消除循环引用
    		SerializerFeature.WriteMapNullValue, // 输出空置字段  
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null  
         // SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null  
         // SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null  
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null  
    };  
    public static String toJsonString(Object object){
    	try {
    		return JSON.toJSONString(object, features);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
    }
    public static String toJsonStringWithTime(Object object){
    	try {
    		return JSON.toJSONString(object,config, features);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
    }
    public static String toJsonStringNotEmpty(Object object){
    	try {
    		return JSON.toJSONString(object,SerializerFeature.DisableCircularReferenceDetect);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
    }
    public static String toJsonStringNotEmptyWithTime(Object object){
    	try {
    		return JSON.toJSONString(object,config,SerializerFeature.DisableCircularReferenceDetect);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
    }
    /** 
     * 把jsonString转化为一个bean对象数组 
     * @param jsonString 
     * @param beanClazz 
     * @return 
     */ 
    public static <T> T jsonString2Bean(String jsonString ,Class<T> beanClazz){  
        if(jsonString == null || "".equals(jsonString.trim())){  
            return null;  
        }  
        try {
        	return JSON.parseObject(jsonString, beanClazz);
		} catch (Exception e) {
			logger.error(jsonString +":" + e.getMessage());
		}
        return null;  
    }  
      
    /** 
     * 把jsonString转化为多个bean对象数组 
     * @param jsonString 
     * @param beanClazz 
     * @return 
     */  
    public static <T> List<T> jsonString2BeanList(String jsonString ,Class<T> beanClazz){  
        if(jsonString == null){  
            return null;  
        }  
        List<T> objects = new ArrayList<T>(); 
        try {
        	objects = JSON.parseArray(jsonString, beanClazz);  
		} catch (Exception e) {
			logger.error(jsonString +":" + e.getMessage());
		}
        return objects;  
    }  
    /**
     * <p>description:统一获取返回的json格式</p>
     * @param err
     * @param msg
     * @return
     */
	public static String getResponseJson(String err,String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("err", err);
		map.put("msg", msg);
		String json = toJsonString(map);
		return json;
	}
	
	/**
     * <p>description:统一获取返回的json格式</p>
	 * @param statusCode
	 * @param msg
	 * @param content
	 * @return
	 */
    public static String getResponseJson(int statusCode,String msg, Object content){
    	return getResponseJson(statusCode, msg, content, null);
    }
	
    /**
     * <p>description:统一获取返回的json格式</p>
     * @param statusCode
     * @param msg
     * @param content
     * @param callback
     * @return
     */
	public static String getResponseJson(int statusCode,String msg, Object content, String callback){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", statusCode);
		map.put("msg", msg);
		if(content == null){
			content = "null";
		}
		map.put("data", content);
		String json = toJsonString(map);
		if(callback != null && !"".equals(callback.trim())){
			json = callback.concat("(").concat(json).concat(")");
		}
		return json;
	}
	
	

	/**
	 * <p>description:获取忽略空值的json格式(忽略""和NULL)</p>
	 * @param err
	 * @param msg
	 * @return
	 */
	public static String getResponseJsonNotEmpty(String err,String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("err", err);
		map.put("msg", msg);
		String json = toJsonStringNotEmpty(map);
		return json;
	}
	
	/**
	 * <p>description:获取忽略空值的json格式(忽略""和NULL)</p>
	 * @param statusCode
	 * @param msg
	 * @param content
	 * @return
	 */
	public static String getResponseJsonNotEmpty(int statusCode,String msg, Object content){
		return getResponseJsonNotEmpty(statusCode, msg, content, null);
	}
	
	/**
	 * <p>description:获取忽略空值的json格式(忽略""和NULL)</p>
	 * @param statusCode
	 * @param msg
	 * @param content
	 * @param callback
	 * @return
	 */
	public static String getResponseJsonNotEmpty(int statusCode,String msg, Object content, String callback){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", statusCode);
		map.put("msg", msg);
		map.put("data", content);
		String json = toJsonStringNotEmpty(map);
		if(callback != null && !"".equals(callback.trim())){
			json = callback.concat("(").concat(json).concat(")");
		}
		return json;
	}
	/**
	 * <p>description:统一获取返回的带时间格式的json格式</p>
	 * @param err
	 * @param msg
	 * @return
	 */
	public static String getResponseJsonWithTime(String err,String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("err", err);
		map.put("msg", msg);
		String json = toJsonStringWithTime(map);
		return json;
	}
	
	/**
	 * <p>description:统一获取返回的带时间格式的json格式</p>
	 * @param statusCode
	 * @param msg
	 * @param content
	 * @return
	 */
	public static String getResponseJsonWithTime(int statusCode,String msg, Object content){
		return getResponseJsonWithTime(statusCode, msg, content, null);
	}
	
	/**
	 * <p>description:统一获取返回的带时间格式的json格式</p>
	 * @param statusCode
	 * @param msg
	 * @param content
	 * @param callback
	 * @return
	 */
	public static String getResponseJsonWithTime(int statusCode,String msg, Object content, String callback){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", statusCode);
		map.put("msg", msg);
		if(content == null){
			content = "null";
		}
		map.put("data", content);
		String json = toJsonStringWithTime(map);
		if(callback != null && !"".equals(callback.trim())){
			json = callback.concat("(").concat(json).concat(")");
		}
		return json;
	}
	
	
	
	/**
	 * <p>description:获取忽略空值的带时间格式的json格式(忽略""和NULL)</p>
	 * @param err
	 * @param msg
	 * @return
	 */
	public static String getResponseJsonNotEmptyWithTime(String err,String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("err", err);
		map.put("msg", msg);
		String json = toJsonStringNotEmptyWithTime(map);
		return json;
	}
	
	/**
	 * <p>description:获取忽略空值的带时间格式的json格式(忽略""和NULL)</p>
	 * @param statusCode
	 * @param msg
	 * @param content
	 * @return
	 */
	public static String getResponseJsonNotEmptyWithTime(int statusCode,String msg, Object content){
		return getResponseJsonNotEmptyWithTime(statusCode, msg, content, null);
	}
	
	/**
	 * <p>description:获取忽略空值的带时间格式的json格式(忽略""和NULL)</p>
	 * @param statusCode
	 * @param msg
	 * @param content
	 * @param callback
	 * @return
	 */
	public static String getResponseJsonNotEmptyWithTime(int statusCode,String msg, Object content, String callback){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", statusCode);
		map.put("msg", msg);
		map.put("data", content);
		String json = toJsonStringNotEmptyWithTime(map);
		if(callback != null && !"".equals(callback.trim())){
			json = callback.concat("(").concat(json).concat(")");
		}
		return json;
	}
}
