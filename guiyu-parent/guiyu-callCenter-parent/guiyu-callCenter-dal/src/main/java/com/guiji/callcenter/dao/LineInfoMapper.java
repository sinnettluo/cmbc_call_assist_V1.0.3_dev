package com.guiji.callcenter.dao;


import com.guiji.callcenter.dao.entity.LineInfo;
import com.guiji.callcenter.dao.entity.LineInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LineInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table line_info
     *
     * @mbggenerated
     */
    int countByExample(LineInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table line_info
     *
     * @mbggenerated
     */
    int deleteByExample(LineInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table line_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer lineId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table line_info
     *
     * @mbggenerated
     */
    int insert(LineInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table line_info
     *
     * @mbggenerated
     */
    int insertSelective(LineInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table line_info
     *
     * @mbggenerated
     */
    List<LineInfo> selectByExample(LineInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table line_info
     *
     * @mbggenerated
     */
    LineInfo selectByPrimaryKey(Integer lineId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table line_info
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") LineInfo record, @Param("example") LineInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table line_info
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") LineInfo record, @Param("example") LineInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table line_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LineInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table line_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LineInfo record);
}