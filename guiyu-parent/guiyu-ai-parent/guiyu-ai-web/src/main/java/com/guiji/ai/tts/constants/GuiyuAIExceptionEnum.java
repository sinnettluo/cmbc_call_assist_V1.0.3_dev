package com.guiji.ai.tts.constants;

import com.guiji.common.exception.ExceptionEnum;

public enum GuiyuAIExceptionEnum implements ExceptionEnum{

	EXCP_AI_TRANSFER("0500001","语音合成失败"),
	EXCP_AI_GET_TTS("0500002","没有获取到TTS"),
	EXCP_AI_GET_GPU("0500003","没有获取到可用GPU"),
	EXCP_AI_CHANGE_TTS("0500004","模型切换失败");
	
	//返回码
	private String errorCode;
	//返回信息
	private String msg;
	
	GuiyuAIExceptionEnum(String errorCode, String msg) {
		this.errorCode = errorCode;
		this.msg = msg;
	}
	
	//根据枚举的code获取msg的方法
	public static GuiyuAIExceptionEnum getMsgByErrorCode(String errorCode){
		for(GuiyuAIExceptionEnum guiyuNasExceptionEnum : GuiyuAIExceptionEnum.values()) {
			if(guiyuNasExceptionEnum.getErrorCode().equals(errorCode)){
				return guiyuNasExceptionEnum;
			}
		}
		return null;
	}

	@Override
	public String getName() {
		return this.name();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
