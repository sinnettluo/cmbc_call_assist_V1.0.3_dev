package com.guiji.ai.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Map;

/**
 * 语音合成返回对象
 * Created by ty on 2018/11/13.
 */
@ApiModel(value="TtsRspVO对象",description="语音合成返回对象")
public class TtsRspVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="业务ID")
    private String busId;

    @ApiModelProperty(value="处理状态:0-未处理;1-处理中;2-已完成;3-处理失败",required=true)
	private Integer status;
    
    @ApiModelProperty(value="失败原因")
    private String errorMsg;

    @ApiModelProperty(value="文本和音频下载地址,key是文本value是音频")
    private Map<String,String> audios;

	
    public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Map<String, String> getAudios() {
		return audios;
	}

	public void setAudios(Map<String, String> audios) {
		this.audios = audios;
	}

	@Override
	public String toString() {
		return "TtsRspVO{"
				+ "busId=" + busId + ", "
				+ "status=" + status + ", "
				+ "errorMsg=" + errorMsg + ", "
				+ "audios=" + audios
				+ "}";
	}
}
