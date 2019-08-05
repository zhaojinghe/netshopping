package com.school.controller.pre;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.pojo.Product;
import com.school.service.category.CategoryService;
import com.school.service.product.ProductService;
import com.school.util.EmptyUtils;
import com.school.util.Pager;
import com.school.util.ProductCategoryVo;


@Controller
public class PreProductController {
	
	   @Resource
	   private ProductService productService;
	   @Resource
	   private CategoryService   categoryService;


	  

	    /**
	     * 查询商品列表
	     *
	     * @param 
	     * @param 
	     * @return
	     */
	   @RequestMapping("/Product")
	    public String queryProductList(@RequestParam(value="category",required=false)Integer category,
	    		@RequestParam(value="level",defaultValue="0",required=false)Integer level,
	    		@RequestParam(value="page",defaultValue="1",required=false)Integer page,
	    		@RequestParam(value="keyWord",required=false)String keyWord,
	    		Model model) throws Exception{
	       
		   System.out.println("=================controller==============="+keyWord);
	    	PageHelper.startPage(page,5);
	        Pager pageurl=new Pager();
	        pageurl.setUrl("/Product?action=queryProductList&level="+level+"&category="+category+" ");
	        List<ProductCategoryVo> productCategoryVoList = categoryService.queryAllProductCategoryList();
	        
	        List<Product> productList = productService.findAllProductPage(category,keyWord);
	        PageInfo<Product>  pageproduct=new PageInfo<>(productList);
	        
	        model.addAttribute("productList", productList);
	        model.addAttribute("pager", pageproduct);
	        model.addAttribute("pageurl", pageurl);
	        model.addAttribute("keyWord", keyWord);
	        model.addAttribute("productCategoryVoList", productCategoryVoList);
	        
	        return "pre/product/queryProductList";
	    }
	    /**
	     *
	     * @param request
	     * @param response
	     * @return
	     */
	   @RequestMapping("/productdeatil")
	    public String queryProductDeatil(@RequestParam("id")Integer id,Model model,HttpServletRequest request) throws Exception{
	  
	        Product product = productService.findById(id);
	        List<ProductCategoryVo> productCategoryVoList = categoryService.queryAllProductCategoryList();
	       model.addAttribute("product", product);
	       model.addAttribute("productCategoryVoList", productCategoryVoList);
	        addRecentProduct(request,product);
	        return "/pre/product/productDeatil";
	    }
	    /**
	     * 查询最近商品
	     * @return
	     */
	   @RequestMapping("/recentproducts")
	    private List<Product> queryRecentProducts(HttpServletRequest request)throws Exception{
		   
	        HttpSession session=request.getSession();
	        List<Product> recentProducts= (List<Product>) session.getAttribute("recentProducts");
	        
	        if(EmptyUtils.isEmpty(recentProducts)){
	            recentProducts=new ArrayList<Product>();
	        }
	        return recentProducts;
	    }
	    /**
	     * 添加最近浏览商品
	     * @param request
	     * @param product
	     */
	    private void addRecentProduct(HttpServletRequest request,Product product)throws Exception{
	        HttpSession session=request.getSession();
	        List<Product> recentProducts=queryRecentProducts(request);
	        //判断是否满了
	        if(recentProducts.size()>0 &&  recentProducts.size()==10){
	          recentProducts.remove(0);
	        }
	        recentProducts.add(recentProducts.size(),product);
	        session.setAttribute("recentProducts",recentProducts);
	    }

}
