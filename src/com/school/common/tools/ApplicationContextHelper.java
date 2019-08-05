package com.school.common.tools;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取ApplicationContext上下文
 */
public class ApplicationContextHelper implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.applicationContext = ctx;
	}
	
	 /** 
     * 获取ApplicationContext 
     * @return 
     */  
    public static ApplicationContext getApplicationContext(){  
        return applicationContext;  
    }
    
    public static Object getBean(String beanName) {    
        return applicationContext.getBean(beanName); 
    } 

}
