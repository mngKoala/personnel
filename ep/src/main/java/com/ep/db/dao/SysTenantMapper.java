package com.ep.db.dao;

import com.ep.db.domain.SysTenant;
import com.ep.db.domain.SysTenantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysTenantMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tenant
     *
     * @mbg.generated
     */
    long countByExample(SysTenantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tenant
     *
     * @mbg.generated
     */
    int deleteByExample(SysTenantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tenant
     *
     * @mbg.generated
     */
    int insert(SysTenant record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tenant
     *
     * @mbg.generated
     */
    int insertSelective(SysTenant record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tenant
     *
     * @mbg.generated
     */
    SysTenant selectOneByExample(SysTenantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tenant
     *
     * @mbg.generated
     */
    SysTenant selectOneByExampleSelective(@Param("example") SysTenantExample example, @Param("selective") SysTenant.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tenant
     *
     * @mbg.generated
     */
    List<SysTenant> selectByExampleSelective(@Param("example") SysTenantExample example, @Param("selective") SysTenant.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tenant
     *
     * @mbg.generated
     */
    List<SysTenant> selectByExample(SysTenantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tenant
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysTenant record, @Param("example") SysTenantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tenant
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysTenant record, @Param("example") SysTenantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_tenant
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") SysTenantExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_company
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(String id);
}