package com.chehaha.api.wechat.xiao.menu.pojo;

import com.chehaha.api.wechat.xiao.api.pojo.menu.WechatButtonType;

public enum WechatMenuType {
	Unknown,
	Button,
	View;
	
	public WechatButtonType toWechatType() {
		return WechatButtonType.values()[this.ordinal()-1];
	}
	
}
