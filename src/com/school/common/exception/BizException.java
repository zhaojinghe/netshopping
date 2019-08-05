package com.school.common.exception;
/**
 * 
 * <p>CustomException.java</p>
 * <p>description:自定义Exception异常，用于异常原因可以恢复或用异常传输数据的情景</p>
 * @author simon
 * @version 1.1  20170701
 *
 */
public class BizException extends Exception {

	/**
	 * 默认serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 构造函数
	 */
	public BizException() {}
	
	/**
	 * 构造函数
	 * @param message	异常信息
	 */
	public BizException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * @param cause		可抛出的异常类
	 */
	public BizException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 构造函数
	 * @param message	异常信息
	 * @param cause		可抛出的异常类
	 */
	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

}
