package com.school.controller.pre;




import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.common.exception.BizException;
import com.school.common.web.BaseController;
import com.school.pojo.News;
import com.school.service.news.NewsService;

@Controller
public class NewsController extends  BaseController  {
    
	@Resource
    private NewsService newsService;

    
    @RequestMapping("/newlist")
    public String index()throws Exception{
        return "/pre/newsList";
    }
    
    /**
	 * 查询新闻详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/pnewsdeatil")
	public String newsDeatil(int id,Model model)  throws BizException{
		
	
			News news=newsService.findNewsById(id);
			
			model.addAttribute("news", news);
			
		
		return "backend/news/newsDetail";
	}

}
