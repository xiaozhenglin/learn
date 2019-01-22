package com.chehaha.api.wechat.xiao.custserv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chehaha.common.exception.FrameworkException;
import com.chehaha.api.wechat.xiao.api.WechatConfig;
import com.chehaha.api.wechat.xiao.custserv.pojo.activeresponse.Text;
import com.chehaha.api.wechat.xiao.custserv.pojo.activeresponse.WechatMessage;
import com.chehaha.api.wechat.xiao.custserv.service.ICustomerReplyService;
import com.chehaha.api.wechat.xiao.util.RestUtil;

@Service
public class CustomerServiceReplyImpl implements ICustomerReplyService{
	
	// 设置客服z主动发送消息
	@Override
	public void customerServiceReply(String openId, String content) throws FrameworkException {
		// 配置中获取地址,需整合放置到配置文件
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={:token}";
		//文本消息
		Text text = new Text(content);
		WechatMessage message = new WechatMessage(openId,"text",text);
		RestUtil.postJsonForEntity(url, message, Object.class, WechatConfig.getAccessToken());
	}
	
		
}
