package com.chehaha.api.wechat.xiao.api.service;

import com.chehaha.api.wechat.xiao.api.WechatApiResult;
import com.chehaha.api.wechat.xiao.api.WechatConfig;
import com.chehaha.api.wechat.xiao.api.pojo.menu.WholeMenuData;
import com.chehaha.api.wechat.xiao.util.RestUtil;

public class WechatMenuApi {
	
	private final static Class<?> clazz = WechatMenuApi.class;
	
	public static boolean setMenu(WholeMenuData menu) {
		final String api = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token= {:token}";
		WechatApiResult result = RestUtil.postJsonForEntity(api, menu, WechatApiResult.class,WechatConfig.getAccessToken());
		if (result.isSuccess()) {
			return true;
		}
		throw result.getException(clazz);
	}
	
}
