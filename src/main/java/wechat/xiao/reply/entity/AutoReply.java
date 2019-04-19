package com.chehaha.api.wechat.xiao.reply.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.chehaha.api.wechat.xiao.reply.pojo.CodeActive;

@Entity
@Table(name="t_wct_auto_reply")
public class AutoReply implements Serializable{
	
	private static final long serialVersionUID = 2453221838651044062L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="KEYWORD")
	private String keyWord;
	
	@Column(name="PARAM_FLAG")
	private int paramFlag;   //是否包含参数
	
	@Column
	private String text;
	
	@Column
	@Enumerated
	private CodeActive active;
	
	@Column(name="CRE_USER")
	private int creatUser;
	
	@Column(name="CRE_TIME")
	private Date creatTime;
	
	@Column(name="LAST_USER")
	private int lastUser;
	
	@Column(name="LAST_TIME")
	private Date lastTime;
	

	public AutoReply(String keyWord, int paramFlag, String text, CodeActive active, int creatUser,
			Date creatTime, int lastUser, Date lastTime) {
		super();
		this.keyWord = keyWord;
		this.paramFlag = paramFlag;
		this.text = text;
		this.active = active;
		this.creatUser = creatUser;
		this.creatTime = creatTime;
		this.lastUser = lastUser;
		this.lastTime = lastTime;
	}
	
	public AutoReply() {
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

	public int getCreatUser() {
		return creatUser;
	}

	public void setCreatUser(int creatUser) {
		this.creatUser = creatUser;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public int getLastUser() {
		return lastUser;
	}

	public void setLastUser(int lastUser) {
		this.lastUser = lastUser;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	
	

}
