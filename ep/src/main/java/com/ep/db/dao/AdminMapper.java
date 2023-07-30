package com.ep.db.dao;

import org.apache.ibatis.annotations.Param;
import com.ep.db.domain.Admin;
import com.ep.db.domain.AdminExample;

import java.util.List;

public interface AdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    long countByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int deleteByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int insert(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int insertSelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    Admin selectOneByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    Admin selectOneByExampleSelective(@Param("example") AdminExample example, @Param("selective") Admin.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    List<Admin> selectByExampleSelective(@Param("example") AdminExample example, @Param("selective") Admin.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    List<Admin> selectByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    Admin selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") Admin.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    Admin selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    Admin selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}