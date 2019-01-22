package com.chehaha.api.wechat.xiao.custserv.pojo;

public class RequireWeChatRecords {
	//聊天记录
	private	long starttime;
	private	long endtime;
	private	String msgid;
	private	String number;
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	public long getEndtime() {
		return endtime;
	}
	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public RequireWeChatRecords(long starttime, long endtime, String msgid, String number) {
		super();
		this.starttime = starttime;
		this.endtime = endtime;
		this.msgid = msgid;
		this.number = number;
	}
	public RequireWeChatRecords() {
	}
	
	public static RequireWeChatRecords instance(long start, long end, String string, String string2) {
		return new RequireWeChatRecords(start,end,string,string2);
	}
	
}
