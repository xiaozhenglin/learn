package com.chehaha.api.wechat.xiao.reply.pojo;

import java.util.Date;

import com.chehaha.api.wechat.xiao.api.WechatConfig;

//回复图片消息
public class PhotoMessage {
	
	private String PhotoMsg;
	
	public String getPhotoMsg() {
		return PhotoMsg;
	}

	public void setXML(String openId,String mediaId) {
		String phomessage="<xml>"
				+ "<ToUserName>"+openId+"</ToUserName>"
				+ "<FromUserName>"+WechatConfig.getWechatId()+"</FromUserName>"
				+ "<CreateTime>"+new Date().getTime()+"</CreateTime>"
				+ "<MsgType>image</MsgType>"
				+ "<Image>"
				+ "<MediaId>"+mediaId+"</MediaId>"
				+ "</Image>"
				+ "</xml>";
		this.PhotoMsg=phomessage;
	}
}
