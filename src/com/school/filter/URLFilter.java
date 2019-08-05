package com.school.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.school.pojo.User;



public class URLFilter implements Filter {
	
	private static Logger logger=Logger.getLogger(URLFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
	    HttpServletRequest   req=(HttpServletRequest)request;
		
		HttpServletResponse resp=(HttpServletResponse)response;
		HttpSession  session=req.getSession();//获取登录的session
		
		String path=req.getServletPath();//获取url
		

		logger.debug("==========path======"+path);
	
		
		User user=(User) session.getAttribute("loginUser");
		
	
		
		
			
			
			 /*if(path.contains("index.jsp")) {
				 
				 req.getRequestDispatcher("/index.jsp").forward(req, resp);
				   return;
			 }else if(path.contains("login.jsp")) {
				 
				 req.getRequestDispatcher("/login.jsp").forward(req, resp);
				   return;
			 }else  if(path.contains("register.jsp")) {
				 
				 req.getRequestDispatcher("/register.jsp").forward(req, resp);
				   return;
			 }else  if(path.contains("login")) {
						 
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
				return;
			}*//*else  if(path.contains("Login")) {
				 
				
				 //判断参数是否为空，不null就获取参数
		        if(req.getQueryString()!=null){
		            path+="?"+req.getQueryString();
		        }
		       
		        session.setAttribute("path", path);
		        req.getRequestDispatcher("/index.jsp").forward(req, resp);
				   return;
			}else {
				logger.debug("==================其他=========================");
				 req.getRequestDispatcher("/index.jsp").forward(req, resp);
				   return;
			 }*/
				
			 
			
				
				logger.debug("==================user=====user=========================");
				chain.doFilter(req, resp);
		
			
	
		
		
	}
	
	

}
