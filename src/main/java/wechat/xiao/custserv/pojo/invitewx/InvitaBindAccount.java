package com.chehaha.api.wechat.xiao.custserv.pojo.invitewx;

//邀请用户绑定微信号
public class InvitaBindAccount {
	private String kf_account;
	private String invite_wx;
	public String getKf_account() {
		return kf_account;
	}
	public void setKf_account(String kf_account) {
		this.kf_account = kf_account;
	}
	public String getInvite_wx() {
		return invite_wx;
	}
	public void setInvite_wx(String invite_wx) {
		this.invite_wx = invite_wx;
	}
	public InvitaBindAccount(String kf_account, String invite_wx) {
		super();
		this.kf_account = kf_account;
		this.invite_wx = invite_wx;
	}
	public InvitaBindAccount() {
		super();
	}
}
