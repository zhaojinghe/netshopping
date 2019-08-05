package com.school.controller.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.common.web.BaseController;
import com.school.common.web.Pager;

import com.school.pojo.Product;
import com.school.service.product.ProductService;


@Controller
public class ProductController extends BaseController{
	
	 @Resource
	 private ProductService productService;
	
	 
	 @RequestMapping("/productList")
	 public String product(@RequestParam(required=true,defaultValue="1")Integer page,Model model) {
		 
		 PageHelper.startPage(page,5);
		 List<Product> productList=productService.findAllProduct();
		
		  PageInfo<Product>  listpage=new PageInfo<>(productList);
		 
		  Pager pages=new Pager();
			pages.setUrl("productList");
		  model.addAttribute("productList",productList);
		  model.addAttribute("pager", listpage);
		  model.addAttribute("pageurl", pages);
		  return "backend/product/productList";
	 }
	 
	 
	 @RequestMapping("/productid")
	 public String productid(@RequestParam("id")Integer id,Model model) {
		 Product productList=productService.findById(id);
		 
		
		  model.addAttribute("productList",productList);
		
		  return "backend/product/toModifyProduct";
	 }
	 
	 @RequestMapping("/add")
	 public String add() {
		
		  return "backend/product/toAddProduct";
	 }
	 
	 @RequestMapping("/addp")
	 public String addproduct(Product product) {
		 int num=productService.addProduct(product);
		  
		 return "redirect:productList";
	 }
	 
	 @RequestMapping("/modify")
	 public String modifyproduct(Product product) {
		 int num=productService.modifyProduct(product);
		  
		  return "redirect:productList";
	 }
	 
	 @RequestMapping("/deleteById")
	 public String del(@RequestParam("id")Integer id) {
		 int num=productService.delProduct(id);
		  
		  return "redirect:productList";
	 }
	 

}
