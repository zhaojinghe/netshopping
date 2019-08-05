package com.school.service.category;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.dao.ProductCategoryMapper;
import com.school.pojo.ProductCategory;
import com.school.pojo.ProductCategoryExample;
import com.school.util.ProductCategoryVo;
@Service
@Transactional
public class CategoryServiceImpl  implements CategoryService{

	@Resource
	private  ProductCategoryMapper productCategoryMapper;
	
	@Override
	public List<ProductCategory> findByAllCategory() {
		// TODO Auto-generated method stub
		return productCategoryMapper.selectByExample(null);
	}

	@Override
	public int addProductCategory(ProductCategory pc) {
		// TODO Auto-generated method stub
		return productCategoryMapper.insertSelective(pc);
	}

	@Override
	public List<ProductCategory> findByFirst() {
		// TODO Auto-generated method stub
		ProductCategoryExample  example=new ProductCategoryExample();
		example.createCriteria().andTypeEqualTo(1).andParentidEqualTo(0);
		return productCategoryMapper.selectByExample(example);
	}

	@Override
	public List<ProductCategory> findBySecond(int level1Id) {
		// TODO Auto-generated method stub
		ProductCategoryExample  example=new ProductCategoryExample();
		example.createCriteria().andTypeEqualTo(2).andParentidEqualTo(level1Id);
		return productCategoryMapper.selectByExample(example);
	}

	@Override
	public List<ProductCategory> findByThird(int level2Id) {
		// TODO Auto-generated method stub
		ProductCategoryExample  example=new ProductCategoryExample();
		example.createCriteria().andTypeEqualTo(3).andParentidEqualTo(level2Id);
		return productCategoryMapper.selectByExample(example);
	}

	@Override
	public ProductCategory findByid(int id) {
		// TODO Auto-generated method stub
		return productCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delProductCategory(int id,int type) {
		// TODO Auto-generated method stub
		ProductCategoryExample  example=new ProductCategoryExample();
		example.createCriteria().andIdEqualTo(id).andTypeEqualTo(type);
		
		return productCategoryMapper.deleteByExample(example);
	}

	@Override
	public int modifyProductCategory(ProductCategory pc) {
		// TODO Auto-generated method stub
		return productCategoryMapper.updateByPrimaryKeySelective(pc);
	}

	@Override
	public List<ProductCategoryVo> queryAllProductCategoryList() {
		// TODO Auto-generated method stub
		 //查询一级分类的列表
        List<ProductCategoryVo> productCategory1VoList = new ArrayList<ProductCategoryVo>();
        //查询一级分类
        List<ProductCategory> productCategory1List =this.findByFirst();
        //查询二级分类
        for (ProductCategory product1Category : productCategory1List) {
            //封装一级分类
            ProductCategoryVo productCategoryVo = new ProductCategoryVo();
            productCategoryVo.setProductCategory(product1Category);
            List<ProductCategoryVo> productCategoryVo1ChildList = new ArrayList<ProductCategoryVo>();
            //根据一级分类查询二级分类
            List<ProductCategory> productCategory2List = this.findBySecond(product1Category.getId());
            
            
            for (ProductCategory productCategory2 : productCategory2List) {
            	
                ProductCategoryVo productCategoryVo2 = new ProductCategoryVo();
                
                productCategoryVo1ChildList.add(productCategoryVo2);
                
                productCategoryVo2.setProductCategory(productCategory2);
                
                List<ProductCategoryVo> productCategoryVo2ChildList = new ArrayList<ProductCategoryVo>();
                
                productCategoryVo2.setProductCategoryVoList(productCategoryVo2ChildList);
                //根据二级分类查询三级分类的列表
                List<ProductCategory> productCategory3List = this.findByThird(productCategory2.getId());
                
                for (ProductCategory productCategory3 : productCategory3List) {
                    ProductCategoryVo productCategoryVo3 = new ProductCategoryVo();
                    productCategoryVo3.setProductCategory(productCategory3);
                    productCategoryVo2ChildList.add(productCategoryVo3);
                }
            }
            productCategoryVo.setProductCategoryVoList(productCategoryVo1ChildList);
            productCategory1VoList.add(productCategoryVo);
        }
        return productCategory1VoList;
	}
	
	

}
