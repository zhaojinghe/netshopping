package com.school.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.log4j.Logger;


/**
 * BeantUtils的扩展方法，支持对Date类型的转换。
 * 
 */
public class BeanUtil extends BeanUtils {

    /** 记录日志 */
    private static Logger logger = Logger.getLogger(BeanUtil.class);

    /** 注册DateConvert **/
    static {
        ConvertUtils.register(new DateConvert(), java.util.Date.class);
        ConvertUtils.register(new DateConvert(), java.sql.Date.class);
        ConvertUtils.register(new DateConvert(), java.sql.Timestamp.class);
        ConvertUtils.register(new LongConverter(), Long.class);  
        ConvertUtils.register(new ShortConverter(), Short.class);  
        ConvertUtils.register(new IntegerConverter(), Integer.class);  
        ConvertUtils.register(new DoubleConverter(), Double.class);  
        ConvertUtils.register(new BigDecimalConverter(), BigDecimal.class);
    }

    /**
     * 功能描述: 重写复制方法<br>
     */
    public static void copyProperties(Object dest, Object orig) {
        try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException e) {
			logger.error("Bean转换失败", e);
		} catch (InvocationTargetException e) {
			logger.error("Bean转换失败", e);
		}
    }

    /**
     * 功能描述: Map中的数据装载到JavaBean中<br>
     * 
     * @param bean JavaBean
     * @param properties Map对象
     */
    public static void populate(Object bean, Map properties) {
        try {
			BeanUtils.populate(bean, properties);
		} catch (IllegalAccessException e) {
			logger.error("Bean转换失败", e);
		} catch (InvocationTargetException e) {
			logger.error("Bean转换失败", e);
		}
    }
    /**
     * <p>description:生成JavaBean，并将特定单一数据装载到JavaBean中</p>
     * 
     * @param name
     * @param value
     * @param clazz
     * @return
     */
    public static <T> T toBean(String name,Object value,Class<T> clazz){
    	if (StringUtils.isEmpty(value) || StringUtils.isBlank(name))
			return null;
    	try {
			T obj = clazz.newInstance();
			setProperty(obj, name, value);
			return obj;
		} catch (InstantiationException e) {
			logger.error("Bean转换失败", e);
		} catch (IllegalAccessException e) {
			logger.error("Bean转换失败", e);
		} catch (InvocationTargetException e) {
			logger.error("Bean转换失败", e);
		}
    	return null;
    }
    /**
     * 功能描述：生成JavaBean，并将Map中的数据装载到JavaBean中<br>
     * 
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T toBean(Map map,Class<T> clazz){
    	try {
			T obj = clazz.newInstance();
			populate(obj, map);
			return obj;
		} catch (InstantiationException e) {
			logger.error("Bean转换失败", e);
		} catch (IllegalAccessException e) {
			logger.error("Bean转换失败", e);
		}
		return null;
    }
    /**
     * 内部类，日期转换扩展。
     */
    static class DateConvert implements Converter {
        public Object convert(Class arg0, Object arg1) {
        	if(arg1 == null){
        		return null;
        	}
        	if(arg1 instanceof java.sql.Timestamp || arg1 instanceof java.util.Date || arg1 instanceof java.sql.Date){
        		return arg1;
        	}
            String p = (String) arg1;
            if (p.trim().length() == 0) {
                return null;
            }
            Object result = null;
            SimpleDateFormat df = null;
            try {
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                result = df.parse(p.trim());
            } catch (ParseException e) {
                try {
                    df = new SimpleDateFormat("yyyy-MM-dd");
                    result = df.parse(p.trim());
                } catch (ParseException ex) {
                    logger.error("不能转换为日期格式。", e);
                }
            }
            return result;
        }
    }

    /**
     * Long类，null不转换。
     */
    static class LongConverter implements Converter {
        public Object convert(Class arg0, Object arg1) {
        	if (StringUtils.isBlank(arg1))
        		return null;
        	Object temp = NumberUtil.dealLong(arg1);
        	if(temp == null)
        		logger.error("不能转换为Long格式");
            return temp;
        }
    }
    
    /**
     * Integer类，null不转换。
     */
    static class IntegerConverter implements Converter {
        public Object convert(Class arg0, Object arg1) {
        	if (StringUtils.isBlank(arg1))
        		return null;
        	Object temp = NumberUtil.dealInteger(arg1);
        	if(temp == null)
        		logger.error("不能转换为Integer格式");
            return temp;
        }
    }
    
    /**
     * Short类，null不转换。
     */
    static class ShortConverter implements Converter {
        public Object convert(Class arg0, Object arg1) {
        	if (StringUtils.isBlank(arg1))
        		return null;
        	Object temp = NumberUtil.dealShort(arg1);
        	if(temp == null)
        		logger.error("不能转换为Short格式");
            return temp;
        }
    }
    
    /**
     * Double类，null不转换。
     */
    static class DoubleConverter implements Converter {
        public Object convert(Class arg0, Object arg1) {
        	if (StringUtils.isBlank(arg1))
        		return null;
        	Object temp = NumberUtil.dealDouble(arg1);
        	if(temp == null)
        		logger.error("不能转换为Double格式");
            return temp;
        }
    }
    
    /**
     * BigDecimal类，null不转换。
     */
    static class BigDecimalConverter implements Converter {
        public Object convert(Class arg0, Object arg1) {
        	if (StringUtils.isBlank(arg1))
        		return null;
        	Object temp = NumberUtil.dealBigDecimal(arg1);
        	if(temp == null)
        		logger.error("不能转换为BigDecimal格式");
            return temp;
        }
    }
    

    
}
