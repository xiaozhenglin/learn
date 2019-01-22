package com.chehaha.api.wechat.xiao.api.pojo.custserv;

import java.io.File;

import com.chehaha.common.util.string.StringUtil;


public class CustomerServiceForm extends CustomerServiceUser {

	private static final long serialVersionUID = -7827934789505342067L;
	private File avatar; 
	
	public boolean hasAvatar() {
		return avatar != null;
	}
	
	public boolean hasWechatAccount() {
		return StringUtil.isNotEmpty(getWechatAccount());
	}
	
	public File getAvatar() {
		return avatar;
	}
	public void setAvatar(File avatar) {
		this.avatar = avatar;
	}



	

}
