package com.chehaha.api.wechat.xiao.custserv.pojo;

import java.io.Serializable;
import java.util.List;

import com.chehaha.api.wechat.xiao.api.WechatApiResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CsvInfoList extends WechatApiResult implements Serializable{
	
	private static final long serialVersionUID = -3428097487660950718L;
	
	@JsonProperty
	private List<CsvWechatInfomation> kf_list ;

	public List<CsvWechatInfomation> getKf_list() {
		return kf_list;
	}

	public void setKf_list(List<CsvWechatInfomation> kf_list) {
		this.kf_list = kf_list;
	}
	
	@Override
	public boolean isSuccess() {
		return errorCode == null;
	}

}
