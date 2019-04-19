package com.chehaha.api.wechat.xiao.reply.pojo;

import java.util.Date;

import com.chehaha.common.data.pojo.IdNamePair;
import com.chehaha.api.wechat.xiao.reply.entity.AutoReply;

public class ReplyCodeQueryResult{
	 private int id;
	 private String keyWord;
     private int paramFlag;
     private String text;
     private CodeActive active;
     private IdNamePair<Long>  creatUser;
     private Date creatTime;
     private IdNamePair<Long> lastUser;
     private Date lastTime;
     
	public ReplyCodeQueryResult(AutoReply autoReply,IdNamePair<Long>  creatUser,IdNamePair<Long> lastUser) {
		super();
		this.id = autoReply.getId();
		this.keyWord = autoReply.getKeyWord();
		this.paramFlag = autoReply.getParamFlag();
		this.text = autoReply.getText();
		this.active = autoReply.getActive();
		this.creatUser = creatUser;
		this.creatTime = autoReply.getCreatTime(); 
		this.lastUser = lastUser;
		this.lastTime = autoReply.getLastTime();
	}
	public ReplyCodeQueryResult() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public int getParamFlag() {
		return paramFlag;
	}
	public void setParamFlag(int paramFlag) {
		this.paramFlag = paramFlag;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public CodeActive getActive() {
		return active;
	}
	public void setActive(CodeActive active) {
		this.active = active;
	}
	public IdNamePair<Long> getCreatUser() {
		return creatUser;
	}
	public void setCreatUser(IdNamePair<Long> creatUser) {
		this.creatUser = creatUser;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public IdNamePair<Long> getLastUser() {
		return lastUser;
	}
	public void setLastUser(IdNamePair<Long> lastUser) {
		this.lastUser = lastUser;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
     
}
