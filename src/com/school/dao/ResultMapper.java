package com.school.dao;

import com.school.pojo.Result;
import com.school.pojo.ResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface ResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int countByExample(ResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int deleteByExample(ResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int insert(Result record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int insertSelective(Result record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    List<Result> selectByExample(ResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int updateByExampleSelective(@Param("record") Result record, @Param("example") ResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    int updateByExample(@Param("record") Result record, @Param("example") ResultExample example);
}