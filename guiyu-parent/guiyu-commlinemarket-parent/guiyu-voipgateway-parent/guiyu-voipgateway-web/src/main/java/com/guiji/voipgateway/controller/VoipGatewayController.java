package com.guiji.voipgateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guiji.component.result.Result;
import com.guiji.voipgateway.api.VoipGatewayRemote;
import com.guiji.voipgateway.model.Company;
import com.guiji.voipgateway.model.GwDevtbl;
import com.guiji.voipgateway.model.SimPort;
import com.guiji.voipgateway.service.VoipgatewayService;

import lombok.extern.slf4j.Slf4j;

/** 
* @Description: SIM网关服务
* @Author: weiyunbo
* @date 2019年1月28日 下午8:46:55 
* @version V1.0  
*/
@Slf4j
@RestController
public class VoipGatewayController implements VoipGatewayRemote{
	@Autowired
	VoipgatewayService voipgatewayService;
	
	/**
	 * 根据公司编号查找公司信息
	 * @param companyId
	 * @return
	 */
	public Result.ReturnData<Company> queryCompanyById(@RequestParam(value="companyId",required=true) Integer companyId){
		Company company = voipgatewayService.queryCompanyById(companyId);
		return Result.ok(company);
    }
    
    
    /**
     * 根据设备名称查找设备基本信息
     * @param devName
     * @return
     */
	public Result.ReturnData<GwDevtbl> queryCompanyByDevName(@RequestParam(value="devName",required=true) String devName){
		GwDevtbl gwDevtbl = voipgatewayService.queryCompanyByDevName(devName);
		return Result.ok(gwDevtbl);
    }
    
	
	/**
     * 根据设备编号查询设备信息
     * @param devId
     * @return
     */
	public Result.ReturnData<GwDevtbl> queryGwDevByDevId(
			@RequestParam(value="companyId",required=true) Integer companyId,
			@RequestParam(value="devId",required=true) Integer devId){
		GwDevtbl gwDevtbl = voipgatewayService.queryGwDevByDevId(companyId,devId);
		return Result.ok(gwDevtbl);
    }
	
    
    /**
     * 根据客户编号查询客户下所有设备
     * @param devName
     * @return
     */
	public Result.ReturnData<List<GwDevtbl>> queryGwDevtblListByCompId(@RequestParam(value="companyId",required=true) Integer companyId){
		List<GwDevtbl> list = voipgatewayService.queryGwDevtblListByCompId(companyId);
		return Result.ok(list);
    }
    
    
    /**
     * 根据设备编号查询该设备下所有端口情况
     * @param companyId
     * @param devId
     * @return
     */
	public Result.ReturnData<List<SimPort>> querySimPortListByDevId(
			@RequestParam(value="companyId",required=true) Integer companyId,
			@RequestParam(value="devId",required=true) Integer devId){
		List<SimPort> list = voipgatewayService.querySimPortListByDevId(companyId, devId);
		return Result.ok(list);
    }
}