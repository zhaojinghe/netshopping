package com.school.controller.news;

import java.util.List;

import javax.annotation.Resource;

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
import com.school.pojo.News;

import com.school.service.news.NewsService;

@Controller
public class NewsControllerback extends BaseController {
	
	@Resource
	private NewsService newsService;
	
	
	@RequestMapping(value="/news",method=RequestMethod.GET,produces=HTML_PRODUCE_TYPE)
	public String queryNewsList(@RequestParam(required=true,defaultValue="1")Integer page,Model model) {
		
		PageHelper.startPage(page, 10);
	    try {
	    	
	    	  List<News>  list= newsService.queryNewsList();
	    	  
	    	  PageInfo<News>  result=new PageInfo<>(list);
	    	  Pager pages=new Pager();
			  pages.setUrl("news");
	    	  model.addAttribute("newsList",list);
			  model.addAttribute("pager", result);
			  model.addAttribute("pageurl", pages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return "backend/news/newsList";
	}
	/**
	 * 查询新闻详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/newsdeatil")
	public String newsDeatil(int id,Model model)  throws BizException{
		
	
			News news=newsService.findNewsById(id);
			
			model.addAttribute("news", news);
			
		
		return "backend/news/newsDetail";
	}
}
