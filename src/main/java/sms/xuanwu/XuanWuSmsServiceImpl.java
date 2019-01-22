package com.chehaha.api.sms.xuanwu;

import org.springframework.stereotype.Service;

import com.chehaha.api.sms.ISmsServiceAdapter;
import com.chehaha.common.exception.FrameworkException;

/**   
* @Title: XuanWuSmsServiceImpl.java 
* @Description: TODO(用一句话描述该文件做什么) 
* @author James.Wong
* @date 2018年2月9日 
* @version V1.0   
*/
@Service("xuanwu")
public class XuanWuSmsServiceImpl implements ISmsServiceAdapter {

	@Override
	public void send(String mobile, String text) throws FrameworkException {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(String mobile, String template, String... args) throws FrameworkException {
		// TODO Auto-generated method stub

	}

}
