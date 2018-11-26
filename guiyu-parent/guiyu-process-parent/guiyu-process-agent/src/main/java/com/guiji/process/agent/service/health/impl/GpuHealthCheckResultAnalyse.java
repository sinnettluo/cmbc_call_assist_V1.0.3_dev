package com.guiji.process.agent.service.health.impl;

import com.guiji.common.model.process.ProcessStatusEnum;
import com.guiji.process.agent.model.CommandResult;
import com.guiji.process.agent.service.health.IHealthCheckResultAnalyse;
import org.apache.commons.lang3.StringUtils;

public class GpuHealthCheckResultAnalyse implements IHealthCheckResultAnalyse {

    @Override
    public ProcessStatusEnum check(CommandResult cmdResult) {
        if (cmdResult != null && StringUtils.isNotEmpty(cmdResult.getOutput())) {
            cmdResult.getOutput().contains("RUNNING");
            return ProcessStatusEnum.UP;
        }

        return ProcessStatusEnum.DOWN;
    }
}