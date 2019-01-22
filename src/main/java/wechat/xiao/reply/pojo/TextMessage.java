package com.chehaha.api.wechat.xiao.reply.pojo;

import java.util.Date;

import com.chehaha.api.wechat.xiao.api.WechatConfig;


//回复文本消息
public class TextMessage {
	private String msg;
	
	public String getMsg() {
		return msg;
	}


	public void toXml(String openId,String content) {
		String str="<xml>   " + 
				"<ToUserName>"+openId+"</ToUserName>" + 
				"<FromUserName>"+WechatConfig.getWechatId()+"</FromUserName>" + 
				"<CreateTime>"+new Date().getTime()+"</CreateTime>" + 
				"<MsgType>text</MsgType>" + 
				"<Content>"+content+"</Content>" + 
				"</xml>  ";	
		this.msg=str;
	}
}
