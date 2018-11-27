package com.guiji.process.server.service.impl;

import com.guiji.common.exception.GuiyuException;
import com.guiji.common.model.process.ProcessInstanceVO;
import com.guiji.common.model.process.ProcessStatusEnum;
import com.guiji.common.model.process.ProcessTypeEnum;
import com.guiji.process.core.vo.*;
import com.guiji.process.model.ProcessReleaseVO;
import com.guiji.process.server.exception.GuiyuProcessExceptionEnum;
import com.guiji.process.server.model.DeviceProcessConstant;
import com.guiji.process.server.service.IProceseScheduleService;
import com.guiji.process.server.service.IProcessAgentManageService;
import com.guiji.process.server.service.IProcessInstanceManageService;
import com.guiji.process.server.util.DeviceProcessUtil;
import com.guiji.utils.IdGenUtil;
import com.guiji.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProceseScheduleService implements IProceseScheduleService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ProcessManageService deviceManageService;

    @Autowired
    private IProcessInstanceManageService processInstanceManageService;
    @Autowired
    private IProcessAgentManageService processAgentManageService;

    @Override
    public List<ProcessInstanceVO> getTTS(String model, int requestCount) {
        if (StringUtils.isEmpty(model)) {
            throw new GuiyuException(GuiyuProcessExceptionEnum.EXCP_PROCESS_MODEL_NULL);
        }

        return getDevices(ProcessTypeEnum.TTS,model, requestCount);
    }

    @Override
    public List<ProcessInstanceVO> getTTS() {
        return getDevices(ProcessTypeEnum.TTS, null, 65565);
    }

    @Override
    public List<ProcessInstanceVO> getSellbot(int requestCount) {
        return getDevices(ProcessTypeEnum.SELLBOT,null, requestCount);
    }

    @Override
    public boolean release(ProcessReleaseVO processReleaseVO) {

        for (ProcessInstanceVO processInstance:processReleaseVO.getProcessInstanceVOS()) {

            processInstance.setWhoUsed("");

            ProcessInstanceVO processInstanceVOOld= deviceManageService.getDevice(processInstance.getType(), processInstance.getIp(), processInstance.getPort());
            if (processInstanceVOOld != null) {
                if(processInstanceVOOld.getStatus() == ProcessStatusEnum.BUSYING) {
                    processInstance.setStatus(ProcessStatusEnum.UP);
                }
                deviceManageService.updateStatus(processInstance);
            }
        }

        return true;
    }

    @Override
    public boolean releaseTts(String model, List<ProcessInstanceVO> processInstances) {
        for (ProcessInstanceVO processInstance:processInstances) {

            processInstance.setWhoUsed("");
            processInstance.setStatus(ProcessStatusEnum.UP);
            if(deviceManageService.getDevice(processInstance.getType(), processInstance.getIp(), processInstance.getPort()) != null)
            {
                deviceManageService.updateStatus(processInstance);
            }
        }

        return true;
    }

    @Override
    public void restoreTtsModel(String srcModel, String toModel, ProcessInstanceVO processInstance) {

        if(StringUtils.equals(srcModel, toModel))
        {
            return;
        }

        ProcessInstanceVO processInstanceVO = processInstanceManageService.get(processInstance.getIp(), processInstance.getPort());
        if(processInstanceVO == null)
        {
            return;
        }
        processInstanceVO.setWhoUsed(IdGenUtil.uuid());
        processInstanceVO.setProcessKey(toModel);
        deviceManageService.updateStatus(processInstanceVO);


        // 通知更换模型 TODO 同步 并设定TTS的状态
        List<String> parameters = new ArrayList<String>();
        parameters.add(srcModel);
        parameters.add(toModel);
        deviceManageService.cmd(processInstance, CmdTypeEnum.RESTORE_MODEL, parameters);

        //processInstance.setWhoUsed(IdGenUtil.uuid());
        //updateActiveCacheList(DeviceTypeEnum.TTS.name()+ "_" + toModel, processInstance);
    }

    @Override
    public void publishResource(ProcessTypeEnum processTypeEnum, String file) {
        Map<Object, Object> deviceVOMap = (Map<Object, Object>) redisUtil.hmget(DeviceProcessConstant.ALL_DEVIECE_KEY);
        if(deviceVOMap ==  null)
        {
            return;
        }

        CmdTypeEnum cmdType = CmdTypeEnum.PULBLISH_SELLBOT_BOTSTENCE;
        if(processTypeEnum == ProcessTypeEnum.SELLBOT)
        {
            cmdType = CmdTypeEnum.PULBLISH_SELLBOT_BOTSTENCE;
        }
        else if(processTypeEnum == ProcessTypeEnum.FREESWITCH)
        {
            cmdType = CmdTypeEnum.PULBLISH_FREESWITCH_BOTSTENCE;
        }
        else
        {
            return;
        }

        ProcessInstanceVO processInstanceVO = null;
        List<String> parameters = new ArrayList<String>();
        parameters.add(file);

        Map<Object, Object> allAgent = (Map<Object, Object>) processAgentManageService.query();
        if(allAgent == null)
        {
            return;
        }

        for (Map.Entry<Object, Object> agentEnv: allAgent.entrySet()) {

            ProcessInstanceVO agent = (ProcessInstanceVO) agentEnv.getValue();
            deviceManageService.cmd(agent, cmdType, parameters);
        }
    }



    private List<ProcessInstanceVO> getDevices(ProcessTypeEnum processTypeEnum, String key, int requestCount)
    {
        List<ProcessInstanceVO> result = new ArrayList<ProcessInstanceVO>();

        Map<Object, Object> allAgent = (Map<Object, Object>) processAgentManageService.query();
        if(allAgent == null)
        {
            return result;
        }


        String whoUsed = IdGenUtil.uuid();
        int count = 0;
        for (Map.Entry<Object, Object> agentEnv: allAgent.entrySet()) {

            if(requestCount == count)
            {
                break;
            }
            ProcessInstanceVO agent = (ProcessInstanceVO) agentEnv.getValue();
            Map<Object, Object> agentProcesses = (Map<Object, Object>) processInstanceManageService.query(((ProcessInstanceVO)agentEnv.getValue()).getIp());

            boolean hasChanged = false;
            for (Map.Entry<Object, Object> processesEnv: agentProcesses.entrySet()) {
                if(requestCount == count)
                {
                    break;
                }

                ProcessInstanceVO processInstance = (ProcessInstanceVO) processesEnv.getValue();

                boolean keyFlg = true;
                if(StringUtils.isNotEmpty(key))
                {
                    keyFlg = StringUtils.equals(key, processInstance.getProcessKey());
                }

                if(processInstance.getStatus() == ProcessStatusEnum.UP && StringUtils.isEmpty(processInstance.getWhoUsed()) && processInstance.getType() == processTypeEnum && keyFlg)
                {
                    processInstance.setStatus(ProcessStatusEnum.BUSYING);
                    processInstance.setWhoUsed(whoUsed);

                    deviceManageService.updateStatus(processInstance);

                    result.add(processInstance);
                    count++;
                    hasChanged = true;
                }
            }

            if(hasChanged)
            {
                processInstanceManageService.update(agent.getIp(), agentProcesses);
            }
        }
        return  result;
    }

}
