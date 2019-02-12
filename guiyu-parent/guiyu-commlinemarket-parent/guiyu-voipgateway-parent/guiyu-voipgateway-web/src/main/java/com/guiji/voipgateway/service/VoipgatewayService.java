package com.guiji.voipgateway.service;

import java.util.List;

import com.guiji.voipgateway.model.Company;
import com.guiji.voipgateway.model.GwDevtbl;
import com.guiji.voipgateway.model.SimPort;

/** 
* @Description: 语音网关服务
* @Author: weiyunbo
* @date 2019年1月25日 下午6:25:25 
* @version V1.0  
*/
public interface VoipgatewayService {

	/**
	 * 根据设备名称查找设备基本信息
	 * @param devName
	 * @return
	 */
	GwDevtbl queryCompanyByDevName(String devName);
	
	/**
	 * 根据公司编号查找公司信息
	 * @param companyId
	 * @return
	 */
	Company queryCompanyById(Integer companyId);
	
	/**
	 * 根据客户编号查询客户下所有设备
	 * @param companyId
	 * @return
	 */
	List<GwDevtbl> queryGwDevtblListByCompId(Integer companyId);
	
	/**
	 * 根据设备编号查询设备基本信息
	 * @param companyId
	 * @param devId
	 * @return
	 */
	GwDevtbl queryGwDevByDevId(Integer companyId,Integer devId);
	
	/**
	 * 根据设备编号查询该设备下所有端口情况
	 * @param companyId
	 * @param devId
	 * @return
	 */
	List<SimPort> querySimPortListByDevId(Integer companyId,Integer devId);
}
