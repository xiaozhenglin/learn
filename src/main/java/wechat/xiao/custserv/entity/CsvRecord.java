package com.chehaha.api.wechat.xiao.custserv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="T_CSV_RECORD")   //聊天记录表
public class CsvRecord implements Serializable{
	
	private static final long serialVersionUID = 4060138192543982587L;

	@Id
	@Column(name="REC_ID")
	private int recId;    //
	
	@Column(name="ACCOUNT")
	private String worker;
	
	@Column(name="CHAT_TIME")
	private int time;
	
	@Column(name="CUST_OPEN_ID")
	private String openid;
	
	@Column(name="CUST_NAME")
	private String custName;
	
	@Column(name="MSG_TYPE")  //消息方向
	private int opercode;
	
	@Column(name="TEXT")
	private String text;

	public int getRecId() {
		return recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getWorker() {
		return worker;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getOpercode() {
		return opercode;
	}

	public void setOpercode(int opercode) {
		this.opercode = opercode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	
	
}
