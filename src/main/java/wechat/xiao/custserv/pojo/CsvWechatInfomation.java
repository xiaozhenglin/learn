package com.chehaha.api.wechat.xiao.custserv.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CsvWechatInfomation implements Serializable{
	private static final long serialVersionUID = -7536525290096723165L;
	private String kf_account;
	private String kf_headimgurl;
	private String kf_nick;
	private String kf_wx;
	private String invite_wx;
	private int invite_expire_time;
	private String invite_status;
	
	public String getKf_account() {
		return kf_account;
	}
	public void setKf_account(String kf_account) {
		this.kf_account = kf_account;
	}
	public String getKf_headimgurl() {
		return kf_headimgurl;
	}
	public void setKf_headimgurl(String kf_headimgurl) {
		this.kf_headimgurl = kf_headimgurl;
	}
	public String getKf_nick() {
		return kf_nick;
	}
	public void setKf_nick(String kf_nick) {
		this.kf_nick = kf_nick;
	}
	public String getKf_wx() {
		return kf_wx;
	}
	public void setKf_wx(String kf_wx) {
		this.kf_wx = kf_wx;
	}
	public String getInvite_wx() {
		return invite_wx;
	}
	public void setInvite_wx(String invite_wx) {
		this.invite_wx = invite_wx;
	}
	public int getInvite_expire_time() {
		return invite_expire_time;
	}
	public void setInvite_expire_time(int invite_expire_time) {
		this.invite_expire_time = invite_expire_time;
	}
	public String getInvite_status() {
		return invite_status;
	}
	public void setInvite_status(String invite_status) {
		this.invite_status = invite_status;
	}
	
}
