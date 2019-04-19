package com.chehaha.api.wechat.xiao.api.exception;


import java.util.HashMap;

public class CustomerServiceException extends HashMap<String, String>{

	private static final long serialVersionUID = 8175630818210992646L;
		
	public CustomerServiceException() {
		put("65400", "API不可用，即没有开通/升级到新客服功能");
		put("65401", "无效客服帐号");
		put("65403", "客服昵称不合法");
		put("65404", "客服帐号不合法");
		put("65405", "帐号数目已达到上限，不能继续添加");
		put("65406", "已经存在的客服帐号");		
		put("65407", "邀请对象已经是本公众号客服");
		put("65408", "本公众号已发送邀请给该微信号");
		put("65409", "无效的微信号");
		put("65410", "邀请对象绑定公众号客服数量达到上限（目前每个微信号最多可以绑定5个公众号客服帐号）");
		put("65411", "该帐号已经有一个等待确认的邀请，不能重复邀请");
		put("65412", "该帐号已经绑定微信号，不能进行邀请");
		put("40005", "不支持的媒体类型");
		put("40009", "媒体文件长度不合法");
	}
	
}
