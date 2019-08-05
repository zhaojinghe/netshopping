package com.school.common.web;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Cookie工具类
 */
public class CookieUtil {
		
		/**正式上线后启用*/
//		private static String defaultDomain = PropertieUtil.config.get("cookie.defaultDomain");
		private static String defaultDomain =null;
	
		/**
	     * 添加cookie
	     * @param name cookie的key
	     * @param value cookie的value
	     * @param domain domain
	     * ＠param  path path 
	     * @param maxage  最长存活时间 单位为秒
	     * @param response
	     */
	    public static void addCookie(String name ,String value,String domain,
	            int maxage,String path, HttpServletResponse response){
	        Cookie cookie = new Cookie(name,value);
	        if(domain!=null){
	            cookie.setDomain(domain);
	        }
	        cookie.setMaxAge(maxage);
	        cookie.setPath(path);
	        response.addCookie(cookie);
	    }
	     
	    /**
	     * 往根下面存一个cookie
	     * @param name cookie的key
	     * @param value cookie的value
	     * @param domain domain
	     * @param maxage  最长存活时间 单位为秒
	     * @param response
	     */
	    public static void addCookie(String name ,String value,String domain,
	            int maxage, HttpServletResponse response){
	        addCookie(name, value,domain, maxage, "/" , response);
	    }
	     
	    /**
	     * 从cookie值返回cookie值，如果没有返回 null
	     * @param req
	     * @param name
	     * @return cookie的值
	     */
	    public static String getCookie(HttpServletRequest request, String name) {
	        Cookie[] cookies = request.getCookies();
	        if (cookies == null || cookies.length ==0) return null;
	        for (int i = 0; i < cookies.length; i++) {
	            if (cookies[i].getName().equalsIgnoreCase(name)) {
	                return cookies[i].getValue();
	            }
	        }
	        return null;
	    }
	 
	    public static void removeCookie(String name, String domain, HttpServletRequest request, HttpServletResponse response) {
	        String cookieVal = getCookie(request,name);
	        if(cookieVal!=null){
	            CookieUtil.addCookie(name, null, domain, 0, response);
	        }
	    }
	 
	    public static void removeCookie(String name, HttpServletRequest request, HttpServletResponse response) {
	        CookieUtil.removeCookie(name, defaultDomain, request, response);
	    }
	}
    
