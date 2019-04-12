package com.guiji.ai.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.guiji.ai.bean.AsynPostReq;
import com.guiji.ai.bean.SynPostReq;
import com.guiji.ai.bean.TtsRsp;
import com.guiji.ai.common.ReturnData;

/**
 * AI对外服务
 */
@FeignClient("guiyu-ai-web")
public interface IAi {
	
	/**
     * 语音合成（同步）
	 * @throws Exception 
     */
	@PostMapping(value = "synPost")
	public ReturnData<String> synPost(@RequestBody SynPostReq synPostReq) throws Exception;
	
	/**
     * 语音合成（异步）
	 * @throws Exception 
     */
	@PostMapping(value = "asynPost")
	public ReturnData<String> asynPost(@RequestBody AsynPostReq asynPostReq) throws Exception;
	
	/**
	 * 根据busiId查询TTS处理结果
	 */
	@GetMapping(value = "getResultByBusId")
	public ReturnData<TtsRsp> getResultByBusId(String busId);
}
