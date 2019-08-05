package com.school.common.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.school.common.utils.NumberUtil;
import com.school.common.utils.StringUtils;


public class CallHelper {
	private static DataSource dataSource;
	static{
		dataSource =  (DataSource)ApplicationContextHelper.getBean("DataSource");
	}
	/*日志*/
	private Logger logger = Logger.getLogger(CallHelper.class);
	/*执行后结果集个数,-1说明调用错误*/
	private int rsNum = -1;
	/*OUT p_status*/
	private Integer status;
	/*OUT p_msg*/
	private String msg;
	/*执行后结果集列表*/
	private List<List<Map<String, Object>>> list = new ArrayList<List<Map<String,Object>>>();
	
	/**
	 * 
	 * 执行MySql简易存储过程（该方法不抛出异常，rsNum = -1 说明调用错误，并记录日志）
	 * 
	 * 	MySql简易存储过程定义如下：
	 * 		1、存储过程只有IN和OUT两种参数类型构成
	 * 		2、OUT参数有且只有两个，并且排列在所有IN参数的后面，顺序为
	 * 			OUT `o_status` int,OUT `o_msg` varchar(200)
	 * 		3、可以没有IN参数
	 * 		4、可以有0个，1个或多个结果集返回
	 * 
	 * @param procName	存储过程名称
	 * @param args		IN类型参数(需要和存储过程数量、类型一致)
	 */
	@SuppressWarnings("unchecked")
	public CallHelper(String procName,final Object...args){
		if(!"".equals(procName = StringUtils.trimToEmpty(procName))){
			try {
				SimpleJdbcCall call = new SimpleJdbcCall(dataSource).withProcedureName(procName);
				Map<String, Object> out = call.execute(args);
				status = NumberUtil.dealInteger(out.get("o_status"));
				msg = StringUtils.valueOf(out.get("o_msg"));
				List<Map<String, Object>> temp = null;
				int i = 1;
				while(( temp = (List<Map<String, Object>>) out.get("#result-set-"+i++)) != null){
					list.add(temp);
				}
				rsNum = list.size();
			} catch (Exception e) {
				rsNum = -1;
				logger.error(e.getMessage(),e);
			}
		}
	}
	
	public int getRsNum() {
		return rsNum;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public List<List<Map<String, Object>>> getList() {
		return list;
	}
	
	public List<Map<String, Object>> get(int num){
		if (num<0 || num>=rsNum) {
			return null;
		}
		return list.get(num);
	}
	


}
