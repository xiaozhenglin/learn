package com.chehaha.api.sms;

import com.chehaha.common.exception.FrameworkException;

/**   
* @Title: ISmsService.java 
* @Description: TODO(用一句话描述该文件做什么) 
* @author James.Wong
* @date 2018年2月9日 
* @version V1.0   
*/
public interface ISmsServiceAdapter {

		void send(String mobile,String text) throws FrameworkException;
		
		void send(String mobile,String template,String... args) throws FrameworkException;
}
