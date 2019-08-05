package com.school.common.web;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.school.common.exception.BizException;
import com.school.common.exception.CustomException;
import com.school.common.tools.CallHelper;
import com.school.common.utils.FastJsonUtil;
import com.school.common.utils.StringUtils;



/**
 * Controller基类
 */
public abstract class BaseController {
    /** 记录日志 */

    private static Logger logger = Logger.getLogger(BaseController.class);
    /** 日期格式*/
    protected static final String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式*/
    protected static final String TIME_PATTERN = "HH:mm:ss";
    /** 24小时制日期时间格式*/
    protected static final String DATETIME_PATTERN_24H = "yyyy-MM-dd HH:mm:ss";
    /** html类型响应头信息*/
    protected static final String HTML_PRODUCE_TYPE = "text/html;charset=UTF-8"; 
    /** json类型响应头信息*/
    protected static final String JSON_PRODUCE_TYPE = "application/json;charset=UTF-8";
    /**
     * 页面提交后的参数转换<br>
     * 字符串日期类型转换为java.util.Date类型
     * 字符串日期时间类型转换为java.sql.Timestamp类型
     * @param binder
     */
    @InitBinder
    protected void initDateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        dateFormat.setLenient(true);
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
        
        SimpleDateFormat datetimeFormat = new SimpleDateFormat(DATETIME_PATTERN_24H);
        datetimeFormat.setLenient(true);
        binder.registerCustomEditor(java.sql.Timestamp.class, new CustomDateEditor(datetimeFormat, true));
        
        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_PATTERN);
        timeFormat.setLenient(false);
        binder.registerCustomEditor(java.sql.Time.class, new CustomDateEditor(timeFormat, true));
    }

    /**
     * 根据相对路径取得绝对路径
     * @param request
     * @param relativePath
     * @return
     */
    protected String getRealPath(HttpServletRequest request, String relativePath){
    	return request.getSession().getServletContext().getRealPath(relativePath);
    }
    

	protected String dealException(int status, String msg, Exception e) {
		if (e instanceof CustomException){
			return e.getMessage() ;
		}else if(e instanceof BizException){
			logger.error(e.getMessage(),e);
			return e.getMessage() ;
		}else {
			logger.error(e.getMessage(),e);
			return FastJsonUtil.getResponseJsonNotEmpty(status, msg, null) ;
		}
	}
	protected String dealCall(CallHelper helper, int errorStatus, String errorMsg){
		if (helper.getRsNum() == -1) {
			return FastJsonUtil.getResponseJsonNotEmpty(errorStatus,errorMsg, null);
		} else {
			return FastJsonUtil.getResponseJson(helper.getStatus(), helper.getMsg(), helper.getList());
		}
	}
	protected String dealQueryResult(Object checkObject,Object result){
		if (StringUtils.isEmpty(checkObject)) {
			return FastJsonUtil.getResponseJsonNotEmpty(-1, "查无信息", null);
		} else {
			return FastJsonUtil.getResponseJson(0, "查询成功", result);
		}
	}

	protected String dealSuccessResutl(String msg,Object result){
		return FastJsonUtil.getResponseJson(0, msg, result);
	}
}
