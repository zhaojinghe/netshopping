package  com.school.common.web;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.common.exception.BizException;
import com.school.common.exception.CustomException;
import com.school.common.utils.FastJsonUtil;



/**
 * Controller基类(针对ajax使用的api做了异常处理的优化)
 */
public abstract class BaseApiController {
    /** 记录日志 */
  
    protected static Logger logger = Logger.getLogger(BaseApiController.class);
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
    @ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public String missingServletRequestParameterException(MissingServletRequestParameterException e){
		logger.error(e.getMessage(), e);
		return FastJsonUtil.getResponseJsonNotEmpty(3000, "参数缺失", null);
	}
	
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public String bindException(BindException e){
		logger.error(e.getMessage(), e);
		return FastJsonUtil.getResponseJsonNotEmpty(4000, "参数格式错误", null);
	}
	
	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public String customException(CustomException e){
		return e.getMessage();
	}
	
	@ExceptionHandler(BizException.class)
	@ResponseBody
	public String bizException(BizException e){
		logger.error(e.getMessage(),e);
		return e.getMessage();
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String exception(Exception e) throws Exception{
		logger.error(e.getMessage(), e);
		return FastJsonUtil.getResponseJsonNotEmpty(2000, "系统异常", null);
	}
}
