package com.school.dao;

import com.school.pojo.UserAddress;
import com.school.pojo.UserAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface UserAddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int countByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int deleteByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int insert(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int insertSelective(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    List<UserAddress> selectByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    UserAddress selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int updateByExample(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int updateByPrimaryKeySelective(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int updateByPrimaryKey(UserAddress record);
}