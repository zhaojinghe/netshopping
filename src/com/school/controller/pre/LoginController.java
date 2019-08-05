package com.school.controller.pre;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.common.exception.BizException;
import com.school.common.web.BaseController;

import com.school.pojo.User;
import com.school.service.user.UserService;
import com.school.util.ReturnResult;

@Controller
public class LoginController extends  BaseController{
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	public String login() {
		
		return "pre/login";
	}
	
	@RequestMapping("/Register")
	public String Register() {
		
		return "pre/register";
	}
	
	@RequestMapping("/index")
	public String index() {
		
		return "pre/index";
	}
	
	@RequestMapping(value="/Login",method=RequestMethod.POST,produces=JSON_PRODUCE_TYPE)
	@ResponseBody
	public String dologin(@RequestParam("loginname") String loginname,
			@RequestParam("password") String password,
			HttpServletRequest request) {
	
	    try {
	    	
			User result= userService.login(loginname, password);
			request.getSession().setAttribute("loginUser", result);
		     
		     return dealQueryResult(result, result);
		} catch (BizException e) {
			// TODO Auto-generated catch block
			
			return dealException(2000, "系统异常！", e);
		}
	
	}
	
	
	
	@RequestMapping(value="/register",method=RequestMethod.POST,produces=JSON_PRODUCE_TYPE)
	@ResponseBody
	public String add(User user,HttpServletRequest request) {
		
	    try {
	    	
			int num= userService.addUser(user);
			request.getSession().setAttribute("loginUser", user);
			
			return dealQueryResult(num, num);
		} catch (BizException e) {
			// TODO Auto-generated catch block
			
			return dealException(2000, "系统异常！", e);
		}
		
	}
	
	@RequestMapping(value="/loginout",method=RequestMethod.GET,produces=HTML_PRODUCE_TYPE)
	public String out(HttpServletRequest request) {
		
	    try {
	    	
	    	 request.getSession().getAttribute("loginUser");
	          request.getSession().removeAttribute("loginUser");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return "pre/index";
	}

}
