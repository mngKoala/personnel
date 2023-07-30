package com.ep.db.dao;

import org.apache.ibatis.annotations.Param;
import com.ep.db.domain.Notice;
import com.ep.db.domain.NoticeExample;

import java.util.List;

public interface NoticeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    long countByExample(NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    int deleteByExample(NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    int insert(Notice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    int insertSelective(Notice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    Notice selectOneByExample(NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    Notice selectOneByExampleSelective(@Param("example") NoticeExample example, @Param("selective") Notice.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    List<Notice> selectByExampleSelective(@Param("example") NoticeExample example, @Param("selective") Notice.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    List<Notice> selectByExample(NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    Notice selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") Notice.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    Notice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    Notice selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Notice record, @Param("example") NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Notice record, @Param("example") NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Notice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Notice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_notice
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}