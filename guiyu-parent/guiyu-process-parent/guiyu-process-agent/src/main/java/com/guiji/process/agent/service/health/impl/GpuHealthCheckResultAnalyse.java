package com.guiji.process.agent.service.health.impl;

import com.guiji.common.model.process.ProcessInstanceVO;
import com.guiji.common.model.process.ProcessStatusEnum;
import com.guiji.component.result.Result;
import com.guiji.process.agent.handler.ImClientProtocolBO;
import com.guiji.process.agent.model.CommandResult;
import com.guiji.process.agent.service.health.IHealthCheckResultAnalyse;
import com.guiji.process.core.message.CmdMessageVO;
import com.guiji.process.core.vo.CmdTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class GpuHealthCheckResultAnalyse implements IHealthCheckResultAnalyse {
    private static final String CMD_RESULT_CODE99 = "99";

    @Override
    public ProcessStatusEnum check(CommandResult cmdResult) {


        if (cmdResult != null && StringUtils.isNotEmpty(cmdResult.getOutput())) {
            System.out.println("GpuHealthCheckResultAnalyse:" +cmdResult.getOutput());
            if(cmdResult.getOutput().contains("1"))
            {
                return ProcessStatusEnum.UP;
            }
        }

        return ProcessStatusEnum.DOWN;
    }

    @Override
    public void afertPublish(CommandResult cmdResult,ProcessInstanceVO processInstanceVO,List<String> parameters,String reqKey) {

    }

    @Override
    public void afertRestart(CommandResult cmdResult,ProcessInstanceVO processInstanceVO,List<String> parameters,String reqKey) {
        String result = CMD_RESULT_CODE99;
        if (cmdResult != null && StringUtils.isNotEmpty(cmdResult.getOutput())) {
            if(cmdResult.getOutput().contains("start one tts done")){
                result = "6";
            } else {
                result = "7";
            }
        }

        // 发送给服务端
        CmdMessageVO newCmdMsg = new CmdMessageVO();
        newCmdMsg.setCmdType(CmdTypeEnum.AFTER_RESTART);
        newCmdMsg.setProcessInstanceVO(processInstanceVO);
        newCmdMsg.setParameters(parameters);
        newCmdMsg.setCommandResult(result);
        newCmdMsg.setCommandResultDesc(Result.error(result).getMsg());
        newCmdMsg.setReqKey(reqKey);
        ImClientProtocolBO.getIntance().send(newCmdMsg,3);
    }

    @Override
    public void afterRestoreModel(CommandResult cmdResult,ProcessInstanceVO processInstanceVO,List<String> parameters,String reqKey) {
        String result = CMD_RESULT_CODE99;
        if (cmdResult != null && StringUtils.isNotEmpty(cmdResult.getOutput())) {
            if(cmdResult.getOutput().contains("SUCCESS") || cmdResult.getOutput().contains("success")){
                result = "8";
            } else {
                result = "9";
            }
        }

        // 发送给服务端
        CmdMessageVO newCmdMsg = new CmdMessageVO();
        newCmdMsg.setCmdType(CmdTypeEnum.AFTER_RESTORE_MODEL);
        newCmdMsg.setProcessInstanceVO(processInstanceVO);
        newCmdMsg.setParameters(parameters);
        newCmdMsg.setCommandResult(result);
        newCmdMsg.setCommandResultDesc(Result.error(result).getMsg());
        newCmdMsg.setReqKey(reqKey);
        ImClientProtocolBO.getIntance().send(newCmdMsg,3);
    }

    @Override
    public void doNothing(ProcessInstanceVO processInstanceVO,List<String> parameters,String reqKey) {
        String result = "10";
        // 发送给服务端
        CmdMessageVO newCmdMsg = new CmdMessageVO();
        newCmdMsg.setCmdType(CmdTypeEnum.DO_NOTHING);
        newCmdMsg.setProcessInstanceVO(processInstanceVO);
        newCmdMsg.setParameters(parameters);
        newCmdMsg.setCommandResult(result);
        newCmdMsg.setCommandResultDesc(Result.error(result).getMsg());
        newCmdMsg.setReqKey(reqKey);
        ImClientProtocolBO.getIntance().send(newCmdMsg,3);

    }
}
