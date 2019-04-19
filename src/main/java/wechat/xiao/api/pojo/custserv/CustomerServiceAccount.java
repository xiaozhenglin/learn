package com.chehaha.api.wechat.xiao.api.pojo.custserv;


import java.io.Serializable;

import com.chehaha.api.wechat.xiao.api.WechatConfig;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerServiceAccount implements Serializable{


	private static final long serialVersionUID = 656310408972891614L;
	

	@JsonProperty("kf_account")
	private String username;
	private String nickname;
	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username+"@"+WechatConfig.getCustomService().getSuffix();
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	
}
