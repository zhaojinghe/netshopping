package com.school.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;




/**
 * <code>Properties</code>文件工具类
 */
public class PropertieUtil {
	/** 记录日志的变量  */
	private static Logger logger = Logger.getLogger(PropertieUtil.class);
    /** 类实例变量   */
	private static PropertieUtil instance = null;
	/** 系统的默认自定义properties */
	public static Map<String,String> config = null;
	/** 私有构造函数  */
	private PropertieUtil() {
	}
	
	static{
	    config = PropertieUtil.getInstance().getProperties("/service.properties");
	}
	
	/**
	 * 获取实例变量
	 * @return PropertieUtil实例
	 */
	public static PropertieUtil getInstance() {
		if (instance == null) {
			instance = new PropertieUtil();
		}
		return instance;
	}

	/**
	 * 根据<code>Properties</code>文件名取得所有的键值
	 * @param filename 类路径下的<code>Properties</code>文件名
	 * @return
	 */
	public Map<String, String> getProperties(String filename) {
		Map<String, String> map = new HashMap<String, String>();
		InputStream is = null;
		try {
			is = getClass().getResourceAsStream(filename);
			if(is == null){
				return map;
			}
			Properties pro = new Properties();
			pro.load(new InputStreamReader(is, "UTF-8"));
			Enumeration<?> e = pro.propertyNames();
			String key = null;
			String value = null;
			while (e.hasMoreElements()) {
				key = (String) e.nextElement();
				value = pro.getProperty(key);
				map.put(key, value == null ? "" : value.trim());
			}
		} catch (IOException ie) {
			logger.error(ie.getMessage());
		} finally{
		    if(is != null){
		        try {
                    is.close();
                } catch (IOException ie) {
                    logger.error(ie.getMessage());
                }
		    }
		}
		return map;
	}
	
	
	   /**
     * 根据<code>Properties</code>文件名取得所有的键值
     * @param filename 文件绝对路径<code>Properties</code>文件名
     * @return
     */
    public Map<String, String> getPropertiesByFilePath(String filename) {
        Map<String, String> map = new HashMap<String, String>();
        InputStream is = null;
        try {
            is = new FileInputStream(filename);
            Properties pro = new Properties();
            pro.load(is);
            Enumeration<?> e = pro.propertyNames();
            String key = null;
            String value = null;
            while (e.hasMoreElements()) {
                key = (String) e.nextElement();
                value = pro.getProperty(key);
                map.put(key, value == null ? "" : value.trim());
            }
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } finally{
            if(is != null){
                try {
                    is.close();
                } catch (IOException ie) {
                    logger.error(ie.getMessage());
                }
            }
        }        
        return map;
    }

}
