package com.ep.db.dao;

import com.ep.db.domain.HrSalaryMonth;
import com.ep.db.domain.HrSalaryMonthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HrSalaryMonthMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    long countByExample(HrSalaryMonthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    int deleteByExample(HrSalaryMonthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    int insert(HrSalaryMonth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    int insertSelective(HrSalaryMonth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    HrSalaryMonth selectOneByExample(HrSalaryMonthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    HrSalaryMonth selectOneByExampleSelective(@Param("example") HrSalaryMonthExample example, @Param("selective") HrSalaryMonth.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    List<HrSalaryMonth> selectByExampleSelective(@Param("example") HrSalaryMonthExample example, @Param("selective") HrSalaryMonth.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    List<HrSalaryMonth> selectByExample(HrSalaryMonthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    HrSalaryMonth selectByPrimaryKeySelective(@Param("id") String id, @Param("selective") HrSalaryMonth.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    HrSalaryMonth selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    HrSalaryMonth selectByPrimaryKeyWithLogicalDelete(@Param("id") String id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") HrSalaryMonth record, @Param("example") HrSalaryMonthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") HrSalaryMonth record, @Param("example") HrSalaryMonthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(HrSalaryMonth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(HrSalaryMonth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") HrSalaryMonthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_salary_month
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(String id);
}