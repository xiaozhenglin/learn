package com.chehaha.api.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chehaha.common.exception.FrameworkException;

/**
 * @Title: SmsServiceAdapter.java
 * @Description: TODO(用一句话描述该文件做什么)
 * @author James.Wong
 * @date 2018年2月9日
 * @version V1.0
 */
@Service("adapter")
public class SmsServiceAdapter implements ISmsServiceAdapter {

	private enum SmsProduct {
		debug,			//开发
		chuanglan, // 创蓝
		xuanwu; // 玄武科技
	}

	private SmsProduct usedProduct;

	@Value("${third-party.sms.use:chuanglan}")
	public void setUse(String use) {
		this.usedProduct = SmsProduct.valueOf(use);
	}

	private ISmsServiceAdapter getUsedProduct() {
		switch (usedProduct) {
		case chuanglan:
			return chuanlanService;
		case xuanwu:
			return xuanwuService;
		default:
			return noneService;
		}
	}

	@Autowired
	@Qualifier("none")
	private ISmsServiceAdapter noneService;
	
	@Autowired
	@Qualifier("chuanglan")
	private ISmsServiceAdapter chuanlanService;

	@Autowired
	@Qualifier("xuanwu")
	private ISmsServiceAdapter xuanwuService;

	@Override
	public void send(String mobile, String text) throws FrameworkException {
		// TODO Auto-generated method stub
		//记录费用
		getUsedProduct().send(mobile, text);
	}

	@Override
	public void send(String mobile, String template, String... args) throws FrameworkException {
		// TODO Auto-generated method stub
		getUsedProduct().send(mobile, template,args);
	}

}
