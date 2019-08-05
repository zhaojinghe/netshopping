package com.school.controller.user;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.common.exception.BizException;
import com.school.common.web.BaseController;
import com.school.common.web.Pager;
import com.school.pojo.User;
import com.school.service.user.UserService;

@Controller
public class UserController extends  BaseController{
	
	@Resource
	private UserService userService;
	
	
	
	@RequestMapping("/insertuser")
	public String addusers() {
		
		return "backend/user/toAddUser";
	}
	
	
	@RequestMapping("/finduserid")
	public String userid(int id,Model model) {
		
		try {
			User user=userService.findUserById(id);
			
			model.addAttribute("user", user);
			
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "backend/user/toUpdateUser";
	}
	
	
	
	@RequestMapping(value="/userinfo",method=RequestMethod.GET,produces=HTML_PRODUCE_TYPE)
	public String userinfo(HttpServletRequest request) {
		
	    try {
	    	
	    
	         request.getSession().setAttribute("loginUser", request.getSession().getAttribute("loginUser"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return "backend/user/userInfo";
	}
	
	
	@RequestMapping(value="/userlist",method=RequestMethod.GET,produces=HTML_PRODUCE_TYPE)
	public String userlist(@RequestParam(required=true,defaultValue="1")Integer page,Model model) {
		
		PageHelper.startPage(page, 10);
	    try {
	    	
	    	  List<User>  list= userService.findUsersInfo();
	    	  
	    	  PageInfo<User>  result=new PageInfo<>(list);
	    	  Pager pages=new Pager();
			  pages.setUrl("userlist");
	    	  model.addAttribute("userList",list);
			  model.addAttribute("pager", result);
			  model.addAttribute("pageurl", pages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return "backend/user/userList";
	}
	@RequestMapping(value="/modify",method=RequestMethod.POST,produces=HTML_PRODUCE_TYPE)
	public String modify(User user) {
		
	    try {
	    	
	    	  int num= userService.modifyUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return "redirect:userlist";
	}
	
	@RequestMapping(value="/adduser",method=RequestMethod.POST,produces=HTML_PRODUCE_TYPE)
	public String adduser(User user) {
		
	    try {
	    	
	    	  int num= userService.addUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return "redirect:userlist";
	}
	
	@RequestMapping(value="/deluser",method=RequestMethod.GET,produces=HTML_PRODUCE_TYPE)
	public String del(int id) {
		
	    try {
	    	
	    	  int num= userService.delUser(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	    return "redirect:userlist";
	}

}
