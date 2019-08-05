package com.school.service.product;

import java.util.List;

import com.school.pojo.Product;

public interface ProductService {

	Product findById(int id)  ;
	
	List<Product>  findAllProduct();
	
	int  addProduct(Product product);
	
	int modifyProduct(Product product);
	
	int delProduct(int id);
	
	List<Product>  findAllProductPage(int id,String keyWord);
	

}
