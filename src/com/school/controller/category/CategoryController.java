package com.school.controller.category;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.common.web.BaseController;
import com.school.common.web.Pager;
import com.school.pojo.ProductCategory;
import com.school.service.category.CategoryService;

@Controller
public class CategoryController  extends BaseController {
	
	@Resource
	private CategoryService  categoryService; 
	


	 @RequestMapping("/category")
	 public String productcategory(@RequestParam(required=true,defaultValue="1")Integer page,Model model) {
		 
		 PageHelper.startPage(page,5);
		 List<ProductCategory> productCategoryList=categoryService.findByAllCategory();
		
		  PageInfo<ProductCategory>  listpage=new PageInfo<>(productCategoryList);
	
		Pager pages=new Pager();
		pages.setUrl("category");
		  model.addAttribute("productCategoryList",productCategoryList);
		  model.addAttribute("pager", listpage);
		  
		  model.addAttribute("pageurl", pages);
		  
		  System.out.println("=====================");
		  return "backend/productCategory/productCategoryList";
	 }
	 
	 @RequestMapping("/addcategory")
	 public String addcategory() {
		
		  return "backend/productCategory/toAddProductCategory";
	 }
	 
	 @RequestMapping("/docategory")
	 public String docategory(ProductCategory  productCategory) {
		 
		  categoryService.addProductCategory(productCategory);
		
		  return "redirect:category";
	 }
	 
	 @RequestMapping("/modifycategory")
	 public String modifycategory(ProductCategory  productCategory) {
		 
		  categoryService.modifyProductCategory(productCategory);
		
		  return "redirect:category";
	 }
	 
	 @RequestMapping("/delcategory")
	 public String delcategory(@RequestParam("id")Integer id,
			 @RequestParam("type")Integer type) {
		 
		  categoryService.delProductCategory(id, type);
		
		  return "redirect:category";
	 }
	 @RequestMapping("/query1")
	 @ResponseBody
	 public String queryby1() {
		
		 List<ProductCategory> result=categoryService.findByFirst();
		  
		  return dealQueryResult(result, result);
	 }
	 
	 @RequestMapping("/query2")
	 @ResponseBody
	 public String queryby2(@RequestParam("level1Id")Integer level1Id) {
		
		 List<ProductCategory> result=categoryService.findBySecond(level1Id);
		  
		  return dealQueryResult(result, result);
	 }
	 
	 @RequestMapping("/query3")
	 @ResponseBody
	 public String queryby3(@RequestParam("level2Id")Integer level2Id) {
		
		 List<ProductCategory> result=categoryService.findByThird(level2Id);
		  
		  return dealQueryResult(result, result);
	 }
	 
	 @RequestMapping("/querybyid")
	 @ResponseBody
	 public String querybyid(@RequestParam("id")Integer id) {
		
		ProductCategory category=categoryService.findByid(id);
		  List<ProductCategory>  result=new ArrayList<>();
		  result.add(category);
		  return dealQueryResult(result, result);
	 }
}
