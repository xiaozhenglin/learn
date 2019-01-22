package com.chehaha.api.wechat.xiao.custserv.pojo.chartadmin;

import java.io.Serializable;

import com.chehaha.common.util.string.StringUtil;


public class CsvChartListQuery implements Serializable{
	
	private static final long serialVersionUID = -1155210778203061624L;
	
	private String 	keyword;
	
	private String 	account;

	public boolean hasKeyword() {
		return StringUtil.isNotEmpty(keyword);
	}
	public boolean hasAccount() {
		return StringUtil.isNotEmpty(account);
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
}
