package com.chehaha.api.wechat.xiao.api.exception;

import java.util.HashMap;

public class WechatUserException extends HashMap<String, String> {

	
	private static final long serialVersionUID = -310298971391674077L;

	public WechatUserException() {
		put("40029", "錯誤的用戶票據");
		put("40164", "传入的凭证已被使用");
		put("10003","redirect_uri域名与后台配置不一致");
		put("10004","此公众号被封禁");
		put("10005","此公众号并没有这些scope的权限");
		put("10006","必须关注此测试号");
		put("10009","操作太频繁了，请稍后重试");
		put("10010","scope不能为空");
		put("10011","redirect_uri不能为空");
		put("10012","appid不能为空");
		put("10013","state不能为空");
		put("10015","公众号未授权第三方平台，请检查授权状态");
		put("10016","不支持微信开放平台的Appid，请使用公众号Appid");
	}
}
