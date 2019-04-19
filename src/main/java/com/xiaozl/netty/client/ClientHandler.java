package com.xiaozl.netty.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * descripiton: 客户端逻辑处理
 *
 * @author: www.iknowba.cn
 * @date: 2018/3/23
 * @time: 16:50
 * @modifier:
 * @since:
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {
	
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
    	Channel channel = ctx.channel(); 
        System.out.println("服务器消息"+s);
        if(s.equals("010300000012C5C7")) {
            channel.writeAndFlush("010300100020003000b87c");
        }
        if(s.equals("01020000000479C9")) {
            channel.writeAndFlush("01020105618B");
        }
        if(s.equals("01050000FF008C3A")) {
            channel.writeAndFlush("01050000FF008C3A");
        }
    }
    
}
