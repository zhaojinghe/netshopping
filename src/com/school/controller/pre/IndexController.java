package com.school.controller.pre;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.common.web.BaseController;
import com.school.pojo.News;
import com.school.pojo.Product;

import com.school.service.category.CategoryService;
import com.school.service.news.NewsService;
import com.school.service.product.ProductService;
import com.school.util.ProductCategoryVo;


@Controller
public class IndexController extends  BaseController {

	    @Resource
	    private ProductService productService;
	    @Resource
	    private NewsService newsService;
	    @Resource
	    private CategoryService categoryService;

	   
	    /**
	     * 商城主页的方法
	     * @param request
	     * @param response
	     * @return
	     */
	    @RequestMapping("/Home")
	    public String index(Model model)throws Exception {
	        //查询商品分裂
	        List<ProductCategoryVo> productCategoryVoList = categoryService.queryAllProductCategoryList();
	        PageHelper.startPage(1, 5);
	        List<News> news = newsService.getAllNews();
	        
	        PageInfo<News>  newslist=new PageInfo<>(news);
	        
	        //查询一楼
	        for (ProductCategoryVo vo : productCategoryVoList) {
	        	
	            List<Product> productList = productService.findAllProductPage(vo.getProductCategory().getId(),null);
	          
	            vo.setProductList(productList);
	        }
	        //封装返回
	        model.addAttribute("productCategoryVoList", productCategoryVoList);
	        model.addAttribute("news", news);
	        return "/pre/index";
	    }
}
