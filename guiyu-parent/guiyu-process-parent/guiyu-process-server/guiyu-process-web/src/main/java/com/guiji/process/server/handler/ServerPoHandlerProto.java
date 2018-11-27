package com.guiji.process.server.handler;

import com.guiji.process.core.ProcessMsgHandler;
import com.guiji.process.core.message.CmdMessageVO;
import com.guiji.process.core.message.MessageProto;
import com.guiji.process.server.core.ConnectionPool;
import com.guiji.process.server.util.DeviceProcessUtil;
import com.guiji.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

@Component
public class ServerPoHandlerProto extends ChannelInboundHandlerAdapter {

	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {

		String remoteIp = DeviceProcessUtil.getRemoreIp(ctx);
		ConnectionPool.putChannel(remoteIp, ctx);

		MessageProto.Message message = (MessageProto.Message) msg;

		if (message.getType() == 0) {
			if(StringUtils.isNotEmpty(message.getId())) {
				//客户端启动
				System.out.println("客户端:" + "clientId-" + message.getId());
			}
		}

		// ping
		if (message.getType() == 1) {
			System.out.println("服务端收到消息:" +  message.getContent());
			//发送响应
			MessageProto.Message.Builder builder = MessageProto.Message.newBuilder().setType(1);
			builder.setContent("服务端响应:" + "hello");
			ctx.writeAndFlush(builder);
		}
		if (message.getType() == 2) {
			System.out.println("服务端收到消息:" +  message.getContent());
		}

		// ping
		if (message.getType() == 3) {
			CmdMessageVO cmdMessageVO = JsonUtils.json2Bean(message.getContent(),CmdMessageVO.class);
			if(cmdMessageVO.getProcessInstanceVO() != null)
			{
				cmdMessageVO.getProcessInstanceVO().setIp(DeviceProcessUtil.getRemoreIp(ctx));
			}
			ProcessMsgHandler.getInstance().add(cmdMessageVO);
			System.out.println("转换后的bean"+cmdMessageVO.toString());
			//发送响应
			MessageProto.Message.Builder builder = MessageProto.Message.newBuilder().setType(1);
			builder.setContent("服务端响应:" + "hello");
			ctx.writeAndFlush(builder);
		}
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
