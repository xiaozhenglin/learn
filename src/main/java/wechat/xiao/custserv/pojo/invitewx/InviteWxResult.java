package com.chehaha.api.wechat.xiao.custserv.pojo.invitewx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class InviteWxResult {
	@JsonProperty
	private int errcode;
	@JsonProperty
	private String errmsg;
	
	//获取返回消息
	public  String getMsg() {
		switch (this.errcode) {
			case 0:
				return "ok";
			case 65400:
				return "API不可用，即没有开通/升级到新客服功能";
			case 65401:
				return "无效客服帐号";			
			case 65407:
				return "邀请对象已经是本公众号客服";
			case 65408:
				return "本公众号已发送邀请给该微信号";
			case 65409:
				return "无效的微信号";
			case 65410:
				return "邀请对象绑定公众号客服数量达到上限（目前每个微信号最多可以绑定5个公众号客服帐号）";
			case 65411:
				return "该帐号已经有一个等待确认的邀请，不能重复邀请";
			case 65412:
				return "该帐号已经绑定微信号，不能进行邀请";
			default:
				return null;
			}
	}

	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}


	public int getErrcode() {
		return errcode;
	}


	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}


	
}
