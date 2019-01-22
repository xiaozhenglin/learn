package com.chehaha.api.wechat.xiao.custserv.pojo.regist;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class CsvRegistAccountResult {
	@JsonProperty
	private int errcode;
	@JsonProperty
	private String errmsg;
	
	//获取返回消息
	public  String getMsg() {
		switch (this.errcode) {
			case 0:
				return "ok";
			case 65400:
				return "API不可用，即没有开通/升级到新客服功能";
			case 65403:
				return "客服昵称不合法";			
			case 65404:
				return "客服帐号不合法";
			case 65405:
				return "帐号数目已达到上限，不能继续添加";
			case 65406:
				return "已经存在的客服帐号";
			default:
				return "error";
			}
	}
	
	public boolean needDenied() {
		switch (this.errcode) {
			case 65403:
				return true;			
			case 65404:
				return true;
			case 65405:
				return true;
			default:
				return false;
		}
	}

	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}


	public int getErrcode() {
		return errcode;
	}


	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}


	public CsvRegistAccountResult(int errcode, String errmsg) {
		super();
		this.errcode = errcode;
		this.errmsg = errmsg;
	}


	public CsvRegistAccountResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
}
