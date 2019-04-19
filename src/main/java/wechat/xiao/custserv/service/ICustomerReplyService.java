package com.chehaha.api.wechat.xiao.custserv.service;

import com.chehaha.common.exception.FrameworkException;

public interface ICustomerReplyService {

	/**
	 * @param openId
	 * @param content
	 * @throws FrameworkException
	 * 主动发送消息方式的自动回复
	 * 可用  xiao
	 */
	void customerServiceReply(String openId,String content) throws FrameworkException;
}
