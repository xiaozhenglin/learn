package com.chehaha.api.wechat.xiao.custserv.pojo.regist;

public class RequireWxRegistAccount {
	private String kf_account;
	private String nickname;
	public String getKf_account() {
		return kf_account;
	}
	public void setKf_account(String kf_account) {
		this.kf_account = kf_account;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public RequireWxRegistAccount(String kf_account, String nickname) {
		super();
		this.kf_account = kf_account;
		this.nickname = nickname;
	}
	public RequireWxRegistAccount() {
		super();
	}
	
}
