package com.chehaha.api.sms.none;

import org.springframework.stereotype.Service;

import com.chehaha.api.sms.ISmsServiceAdapter;
import com.chehaha.common.exception.FrameworkException;

/**   
* @Title: NoneSmsServiceImpl.java 
* @Description: TODO(用一句话描述该文件做什么) 
* @author James.Wong
* @date 2018年2月10日 
* @version V1.0   
*/
@Service("none")
public class NoneSmsServiceImpl implements ISmsServiceAdapter {

	@Override
	public void send(String mobile, String text) throws FrameworkException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(String mobile, String template, String... args) throws FrameworkException {
		// TODO Auto-generated method stub

	}

}
