package com.chehaha.api.wechat.xiao.reply.pojo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//收到的消息
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WechatUserMessage implements Serializable {

	private static final long serialVersionUID = -1127895785675970128L;
	
	@XmlElement(name="MsgId")
	private long msgId;
	
	@XmlElement(name="ToUserName")
	private String toUser;
	
	@XmlElement(name="FromUserName")
	private String fromUserName;
	
	@XmlElement(name="CreateTime")
	private int createTime;
	
	@XmlElement(name="MsgType")
	private String msgType;  //消息类型
	
	@XmlElement(name="Content")
	private String content;

	@XmlElement(name="MediaId")
	private String MediaId;
	
	@XmlElement(name="PicUrl")
	private String PicUrl;
	
	@XmlElement(name="EventKey")
	private String EventKey;  //事件KEY值
	
	@XmlElement(name="Event")
	private String Event;   //	事件类型
	
	
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getCreateTime() {
		return createTime;
	}
	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getMsgId() {
		return msgId;
	}
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public WechatUserMessage() {
		super();
	}
	
	public WechatUserMessage(String fromUserName) {
		super();
		this.fromUserName = fromUserName;
	}

	@Override
	public String toString() {
		return "WechatUserMessage [fromUser=" + fromUserName + "]";
	}
	

	
	
}
