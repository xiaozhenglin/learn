package com.chehaha.api.wechat.xiao.api.pojo.custserv;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerServiceUser extends CustomerServiceAccount {

	private static final long serialVersionUID = 4534956184482652841L;
	
	@JsonProperty("invite_wx")
	private String wechatAccount;

	public String getWechatAccount() {
		return wechatAccount;
	}

	public void setWechatAccount(String wechatAccount) {
		this.wechatAccount = wechatAccount;
	}
	
}
