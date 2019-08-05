package com.school.service.category;

import java.util.List;

import com.school.pojo.ProductCategory;
import com.school.util.ProductCategoryVo;



public interface CategoryService {
	
	List<ProductCategory>  findByAllCategory();
	List<ProductCategory>  findByFirst();
	List<ProductCategory>  findBySecond(int level1Id);
	List<ProductCategory>  findByThird(int level2Id);
	int addProductCategory(ProductCategory pc);
	int modifyProductCategory(ProductCategory pc);
	ProductCategory  findByid(int id);
	int delProductCategory(int id,int type);
	
	 public List<ProductCategoryVo> queryAllProductCategoryList();
}
