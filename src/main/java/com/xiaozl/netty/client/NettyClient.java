package com.xiaozl.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * descripiton: 客户端
 *
 * @author: www.iknowba.cn
 * @date: 2018/3/23
 * @time: 16:40
 * @modifier:
 * @since:
 */
public class NettyClient {

    private String ip ;

    private int port ;

    private boolean stop = false;

    public NettyClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public NettyClient() {
		// TODO Auto-generated constructor stub
	}
    
    private  Channel connectChannel = null;

	public void run() throws IOException {
        //设置一个多线程循环器
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //启动附注类
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        //指定所使用的NIO传输channel
        bootstrap.channel(NioSocketChannel.class);
        //指定客户端初始化处理
        bootstrap.handler(new ClientIniterHandler());
        try {
            //连接服务
            Channel channel = bootstrap.connect(ip, port).sync().channel();
            this.connectChannel =  channel;

//            while (true) {
//                //向服务端发送内容
//                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//                String content = reader.readLine();
//                if (StringUtils.isNotEmpty(content)) {
//                    if (StringUtils.equalsIgnoreCase(content, "quit")) {
//                        System.exit(1);
//                    }
////                  channel.writeAndFlush(content);
//                }
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e); 
//            System.exit(1);
        } 
//        finally {
//            workerGroup.shutdownGracefully();
//        }
    }

	
    public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public Channel getConnectChannel() {
		return connectChannel;
	}

	public void setConnectChannel(Channel connectChannel) {
		this.connectChannel = connectChannel;
	}

	public static void main(String[] args) throws Exception { 
		List<NettyClient> list = new ArrayList();
		
		Date now = new Date();
    	for(int i =0 ; i<1000;i++) {
    		NettyClient nettyClient = new NettyClient("192.168.1.199",1111); 
    		nettyClient.run();
    		Channel connectChannel = nettyClient.getConnectChannel(); 
    		System.out.println(connectChannel.id()+"--"+connectChannel.isActive());
    		connectChannel.writeAndFlush("CLKJ123123123"+i);
    		list.add(nettyClient);
    	}

		
//		for(NettyClient nettyClient :  list) {
//			Channel connectChannel = nettyClient.getConnectChannel(); 
//    		System.out.println(connectChannel.id()+"--"+connectChannel.isActive());
//    		connectChannel.writeAndFlush(list.indexOf(connectChannel)); 
//		}
		Date now2 = new Date();
		long time = now2.getTime();long time2 = now.getTime();
		long seconds = (time-time2);
		System.out.println(seconds);
		
    }
}
