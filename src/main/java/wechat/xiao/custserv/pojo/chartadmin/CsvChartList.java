package com.chehaha.api.wechat.xiao.custserv.pojo.chartadmin;

public class CsvChartList {
	private int id ;
	private String account;
	private String custName;
	private SendType type;
	private String chatTime;
	private String text;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public SendType getType() {
		return type;
	}
	public void setType(SendType type) {
		this.type = type;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
