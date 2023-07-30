package com.ep.db.dao;

import com.ep.db.domain.HrRecruitPersonRec;
import com.ep.db.domain.HrRecruitPersonRecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HrRecruitPersonRecMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    long countByExample(HrRecruitPersonRecExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    int deleteByExample(HrRecruitPersonRecExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    int insert(HrRecruitPersonRec record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    int insertSelective(HrRecruitPersonRec record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    HrRecruitPersonRec selectOneByExample(HrRecruitPersonRecExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    HrRecruitPersonRec selectOneByExampleSelective(@Param("example") HrRecruitPersonRecExample example, @Param("selective") HrRecruitPersonRec.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    List<HrRecruitPersonRec> selectByExampleSelective(@Param("example") HrRecruitPersonRecExample example, @Param("selective") HrRecruitPersonRec.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    List<HrRecruitPersonRec> selectByExample(HrRecruitPersonRecExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    HrRecruitPersonRec selectByPrimaryKeySelective(@Param("id") String id, @Param("selective") HrRecruitPersonRec.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    HrRecruitPersonRec selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    HrRecruitPersonRec selectByPrimaryKeyWithLogicalDelete(@Param("id") String id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") HrRecruitPersonRec record, @Param("example") HrRecruitPersonRecExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") HrRecruitPersonRec record, @Param("example") HrRecruitPersonRecExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(HrRecruitPersonRec record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(HrRecruitPersonRec record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") HrRecruitPersonRecExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hr_recruit_person_rec
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(String id);
}