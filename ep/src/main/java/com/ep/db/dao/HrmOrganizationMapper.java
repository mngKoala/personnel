package com.ep.db.dao;

import com.ep.db.domain.HrmOrganization;
import com.ep.db.domain.HrmOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HrmOrganizationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    long countByExample(HrmOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    int deleteByExample(HrmOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    int insert(HrmOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    int insertSelective(HrmOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    HrmOrganization selectOneByExample(HrmOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    HrmOrganization selectOneByExampleSelective(@Param("example") HrmOrganizationExample example, @Param("selective") HrmOrganization.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    List<HrmOrganization> selectByExampleSelective(@Param("example") HrmOrganizationExample example, @Param("selective") HrmOrganization.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    List<HrmOrganization> selectByExample(HrmOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    HrmOrganization selectByPrimaryKeySelective(@Param("id") String id, @Param("selective") HrmOrganization.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    HrmOrganization selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    HrmOrganization selectByPrimaryKeyWithLogicalDelete(@Param("id") String id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") HrmOrganization record, @Param("example") HrmOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") HrmOrganization record, @Param("example") HrmOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(HrmOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(HrmOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") HrmOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hrm_organization
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(String id);

    List<HrmOrganization> getOrganizationTree(String id);

    List<HrmOrganization> getCompanyTree(String id);

    List<HrmOrganization> getOrganizationWithPositionTree(String id);

    List<HrmOrganization> getParentTree(String id);
}