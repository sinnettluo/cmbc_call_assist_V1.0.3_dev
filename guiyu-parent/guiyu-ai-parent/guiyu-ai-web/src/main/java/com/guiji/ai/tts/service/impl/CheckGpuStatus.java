package com.guiji.ai.tts.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;

import com.guiji.ai.tts.constants.GuiyuAIExceptionEnum;
import com.guiji.ai.tts.constants.AiConstants;
import com.guiji.common.exception.GuiyuException;
import com.guiji.component.result.Result.ReturnData;
import com.guiji.process.api.IProcessSchedule;
import com.guiji.process.core.vo.ProcessInstanceVO;
import com.guiji.utils.RedisUtil;

public class CheckGpuStatus 
//implements ApplicationRunner
{
	private static Logger logger = LoggerFactory.getLogger(CheckGpuStatus.class);
	RedisUtil redisUtil = new RedisUtil();
	
	@Autowired
	IProcessSchedule iProcessSchedule;

	/**
	 * 初始化
	 */
//	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		logger.info("调用进程管理接口查看GPU分配情况");
		List<ProcessInstanceVO> returnList = null;
		ReturnData<List<ProcessInstanceVO>> returnData = iProcessSchedule.getTTS();
		if(returnData != null && returnData.getBody() != null){
			returnList = returnData.getBody();
		}else{
			throw new GuiyuException(GuiyuAIExceptionEnum.EXCP_AI_GET_TTS);
		}
		logger.info("返回的列表", returnList);
		Collections.sort(returnList, new Comparator<ProcessInstanceVO>() {
			@Override
			public int compare(ProcessInstanceVO o1, ProcessInstanceVO o2) {
				return o1.getDeviceKey().compareTo(o2.getDeviceKey());
			}
		});

		String modelName = returnList.get(0).getDeviceKey();
		List<GuiyuTtsGpu> gpuList = new ArrayList<>();

		for (int i = 0; i < returnList.size(); i++) {
			GuiyuTtsGpu gpu = new GuiyuTtsGpu();
			gpu.setIp(returnList.get(i).getIp());
			gpu.setPort(String.valueOf(returnList.get(i).getPort()));

			if (!returnList.get(i).getDeviceKey().equals(modelName)) {
				redisUtil.lSet(AiConstants.GUIYUTTS + modelName + AiConstants.AVALIABLE, gpuList);
				modelName = returnList.get(i).getDeviceKey();
				gpuList = new ArrayList<>();
			}
			gpuList.add(gpu);
		}
		redisUtil.lSet(AiConstants.GUIYUTTS + modelName + AiConstants.AVALIABLE, gpuList);
	}

}
