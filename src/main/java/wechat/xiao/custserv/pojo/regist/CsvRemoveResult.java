package com.chehaha.api.wechat.xiao.custserv.pojo.regist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class CsvRemoveResult {
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
			case 65401:
				return "无效客服帐号";			
			default:
				return "删除失败，重新尝试删除";
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


	
}
