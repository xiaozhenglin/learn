package com.chehaha.api.wechat.xiao.custserv.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//获取在线客服人员列表
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequireOnLineCustomerService implements Serializable{
	
	private static final long serialVersionUID = -6358979352456942455L;
	
	@JsonProperty
	private String kf_account;
	@JsonProperty
	private int status;
	@JsonProperty
	private String kf_id;
	@JsonProperty
	private int accepted_case;
	

	public String getKf_account() {
		return kf_account;
	}


	public void setKf_account(String kf_account) {
		this.kf_account = kf_account;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getKf_id() {
		return kf_id;
	}


	public void setKf_id(String kf_id) {
		this.kf_id = kf_id;
	}


	public int getAccepted_case() {
		return accepted_case;
	}


	public void setAccepted_case(int accepted_case) {
		this.accepted_case = accepted_case;
	}


	public RequireOnLineCustomerService() {
		super();
	}


	@Override
	public String toString() {
		return "RequireOnLineCustomerService [kf_account=" + kf_account + ", status=" + status + ", kf_id=" + kf_id
				+ ", accepted_case=" + accepted_case + "]";
	}
	
}
