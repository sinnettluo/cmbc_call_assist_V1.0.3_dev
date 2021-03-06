package com.guiji.callcenter.dao;


import com.guiji.callcenter.dao.entity.SimGateway;
import com.guiji.callcenter.dao.entity.SimGatewayExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

public interface SimGatewayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sim_gateway
     *
     * @mbggenerated
     */
    int countByExample(SimGatewayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sim_gateway
     *
     * @mbggenerated
     */
    int deleteByExample(SimGatewayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sim_gateway
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(BigInteger id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sim_gateway
     *
     * @mbggenerated
     */
    int insert(SimGateway record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sim_gateway
     *
     * @mbggenerated
     */
    int insertSelective(SimGateway record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sim_gateway
     *
     * @mbggenerated
     */
    List<SimGateway> selectByExample(SimGatewayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sim_gateway
     *
     * @mbggenerated
     */
    SimGateway selectByPrimaryKey(BigInteger id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sim_gateway
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SimGateway record, @Param("example") SimGatewayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sim_gateway
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SimGateway record, @Param("example") SimGatewayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sim_gateway
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SimGateway record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sim_gateway
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SimGateway record);
}