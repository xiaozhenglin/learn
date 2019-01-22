package com.chehaha.api.wechat.xiao.api.pojo;

import com.chehaha.api.wechat.xiao.api.WechatApiResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WechatTokenResult extends WechatApiResult {

	private static final long serialVersionUID = 2697049409203025173L;

	@JsonProperty("access_token")
	private String token;
	
	@JsonProperty("expires_in")
	private int expire;

	@Override
	public boolean isSuccess() {
		return errorCode == null;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}
	
	
}
