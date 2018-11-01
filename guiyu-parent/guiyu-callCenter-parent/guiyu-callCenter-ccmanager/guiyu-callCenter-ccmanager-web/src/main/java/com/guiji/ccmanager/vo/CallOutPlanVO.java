package com.guiji.ccmanager.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Auther: 黎阳
 * @Date: 2018/10/29 0029 19:02
 * @Description:
 */
@Data
@ApiModel(description= "通话记录")
public class CallOutPlanVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String callId;
    @ApiModelProperty(value = "被叫号码")
    private String phoneNum;
    @ApiModelProperty(value = "客户id")
    private String customerId;
    @ApiModelProperty(value = "模板id")
    private String tempId;
    @ApiModelProperty(value = "线路id")
    private Integer lineId;
    private String serverid;
    private String agentId;
    private Date agentAnswerTime;
    private String agentChannelUuid;
    private String agentGroupId;
    private Date agentStartTime;
    private Date createTime;
    private Date scheduleTime;
    private Date hangupTime;
    private Date answerTime;
    private Integer duration;
    private Integer billSec;
    private Integer callDirection;
    private Integer callState;
    private Integer hangupDirection;
    private String accurateIntent​;
    private String reason;
    private String hangupCode;
    private String originateCmd;
    private String remarks;

    private List<CallOutDetailVO> detailList;

}