package com.school.service.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.dao.ProductMapper;
import com.school.pojo.Product;
import com.school.pojo.ProductExample;
import com.school.util.EmptyUtils;


@Service("productService")
@Transactional(rollbackFor=Exception.class)
public class ProductServiceImpl   implements ProductService {

	@Resource
    private ProductMapper productMapper;
	
	
	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Product> findAllProduct() {
		// TODO Auto-generated method stub
		
		ProductExample example=new ProductExample();
		example.setOrderByClause("id desc");
		return productMapper.selectByExample(example);
	}

	@Override
	public int addProduct(Product product) {
		// TODO Auto-generated method stub
		return productMapper.insertSelective(product);
	}

	@Override
	public int modifyProduct(Product product) {
		// TODO Auto-generated method stub
		return productMapper.updateByPrimaryKeySelective(product);
	}

	@Override
	public int delProduct(int id) {
		// TODO Auto-generated method stub
		return productMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Product> findAllProductPage(int id,String keyWord) {
		// TODO Auto-generated method stub
		
		ProductExample example=new ProductExample();

		
        System.out.println("================"+keyWord);
        
		if(EmptyUtils.isNotEmpty(keyWord)) {
			System.out.println("===========//////////////====="+keyWord);
			example.createCriteria().andNameLike(keyWord);
		}
		example.createCriteria().andCategorylevel1idGreaterThanOrEqualTo(id).andCategorylevel1idLessThanOrEqualTo(id).andCategorylevel3idGreaterThanOrEqualTo(id);

		example.setOrderByClause("id desc");
		
		return productMapper.selectByExample(example);
	}



	
	
	
}
