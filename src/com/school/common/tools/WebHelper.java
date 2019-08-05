package com.school.common.tools;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.school.common.exception.CustomException;
import com.school.common.utils.FastJsonUtil;



public class WebHelper {
	private static Logger logger = Logger.getLogger(WebHelper.class);

    /** 
     * isAjaxRequest:判断请求是否为Ajax请求. <br/> 
     * 
     * @param request 请求对象 
     * @return boolean 
     * @since JDK 1.6 
     */  
    public static boolean isAjaxRequest(HttpServletRequest request){  
    	return request.getHeader("accept").indexOf("application/json") > -1 
    	|| (request.getHeader("X-Requested-With") != null 
    		&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1);
    }  
    /**
     * 
     * 
     * @param response
     * @param message
     */
    public static void string2HttpServletResponse(HttpServletResponse response,String message ){
    	response.reset();
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=utf-8");
    	response.setDateHeader("Expires", 0);
		PrintWriter out = null;
		try {
            out = response.getWriter();
            out.println(message);
            out.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			throw new CustomException(FastJsonUtil.getResponseJsonNotEmpty(2000, "系统异常：IO错误", null));
		} finally {
		    if (out != null) {
		        out.close();
		    }
		}
    }
}
