package com.guiji.process.core.util;

import com.guiji.common.model.process.ProcessInstanceVO;
import com.guiji.common.model.process.ProcessStatusEnum;
import com.guiji.process.core.message.CmdMessageVO;
import com.guiji.process.core.message.CmdMsgTypeEnum;
import com.guiji.process.core.message.CmdProtoMessage;
import com.guiji.process.core.vo.CmdTypeEnum;
import io.netty.buffer.ByteBuf;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.Arrays;

public class CmdMessageUtils {

	public static CmdProtoMessage.ProtoMessage.Builder convert(CmdMessageVO cmdMessageVO) {

		CmdProtoMessage.ProtoMessage.Builder builder = CmdProtoMessage.ProtoMessage.newBuilder().setId("1");
		if (cmdMessageVO != null && cmdMessageVO.getProcessInstanceVO() != null) {
			builder.setReqKey(cmdMessageVO.getReqKey());
			builder.setMsgType(cmdMessageVO.getMsgTypeEnum().getValue());
			builder.setCmdType(cmdMessageVO.getCmdType().getValue());
			builder.setParameters(StringUtils.join(cmdMessageVO.getParameters(),","));
			builder.setPort(cmdMessageVO.getProcessInstanceVO().getPort());
			builder.setProcessType(cmdMessageVO.getProcessInstanceVO().getType().getValue());
			builder.setProcessKey(cmdMessageVO.getProcessInstanceVO().getProcessKey());
			builder.setStatus(cmdMessageVO.getProcessInstanceVO().getStatus().getValue());
			builder.setCmdResult(cmdMessageVO.getCommandResult());
			builder.setCmdResultDesc(cmdMessageVO.getCommandResultDesc());
		}


		return builder;
	}

	public static CmdMessageVO convert(CmdProtoMessage.ProtoMessage message) {
		CmdMessageVO cmdMessageVO = new CmdMessageVO();
		cmdMessageVO.setReqKey(message.getReqKey());
		cmdMessageVO.setParameters(Arrays.asList(message.getParameters().split(",")));
		cmdMessageVO.setCmdType(CmdTypeEnum.valueOf(message.getCmdType()));
		cmdMessageVO.setMsgTypeEnum(CmdMsgTypeEnum.valueOf(message.getMsgType()));

		ProcessInstanceVO processInstanceVO = new ProcessInstanceVO();
		processInstanceVO.setPort(message.getPort());
		processInstanceVO.setProcessKey(message.getProcessKey());
		processInstanceVO.setStatus(ProcessStatusEnum.valueOf(message.getStatus()));
		cmdMessageVO.setProcessInstanceVO(processInstanceVO);

		return cmdMessageVO;
	}
}