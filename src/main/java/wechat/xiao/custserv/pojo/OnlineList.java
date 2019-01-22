package com.chehaha.api.wechat.xiao.custserv.pojo;

import java.util.List;

import com.chehaha.api.wechat.xiao.api.WechatApiResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OnlineList extends  WechatApiResult{
	
	private static final long serialVersionUID = -5877550171450857161L;
	
	private List<RequireOnLineCustomerService> kf_online_list;

	public List<RequireOnLineCustomerService> getKf_online_list() {
		return kf_online_list;
	}

	public void setKf_online_list(List<RequireOnLineCustomerService> kf_online_list) {
		this.kf_online_list = kf_online_list;
	}

	@Override
	public boolean isSuccess() {
		return errorCode == null;
	}
	
	
}
