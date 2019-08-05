package com.school.dao;

import com.school.pojo.ProductCategory;
import com.school.pojo.ProductCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface ProductCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_category
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int countByExample(ProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_category
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int deleteByExample(ProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_category
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_category
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int insert(ProductCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_category
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int insertSelective(ProductCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_category
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    List<ProductCategory> selectByExample(ProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_category
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    ProductCategory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_category
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int updateByExampleSelective(@Param("record") ProductCategory record, @Param("example") ProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_category
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int updateByExample(@Param("record") ProductCategory record, @Param("example") ProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_category
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int updateByPrimaryKeySelective(ProductCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_category
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int updateByPrimaryKey(ProductCategory record);
}