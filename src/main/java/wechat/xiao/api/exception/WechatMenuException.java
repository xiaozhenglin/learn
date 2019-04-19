package com.chehaha.api.wechat.xiao.api.exception;

import java.util.HashMap;

public class WechatMenuException extends HashMap<String, String> {

	
	private static final long serialVersionUID = -310298971391674077L;

	public WechatMenuException() {
		put("40018", "菜单名称太长");
		put("40054", "链接格式不正确");
		put("40001","access_token无效");
	}
}
