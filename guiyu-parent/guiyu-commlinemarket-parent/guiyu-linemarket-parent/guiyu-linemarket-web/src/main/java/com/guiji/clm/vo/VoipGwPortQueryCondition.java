package com.guiji.clm.vo;

import lombok.Data;

/** 
* @Description: voip网关端口查询条件
* @Author: weiyunbo
* @date 2019年1月23日 上午10:40:47 
* @version V1.0  
*/
@Data
public class VoipGwPortQueryCondition {
	//网关ID
	private Integer gwId;
	//网关端口sip账号
	private Integer startSipAccount;
	//归属人
	private String userId;
	//归属企业
	private String orgCode;
}
