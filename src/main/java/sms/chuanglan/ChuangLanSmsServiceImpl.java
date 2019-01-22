package com.chehaha.api.sms.chuanglan;

import com.chehaha.common.exception.code.F7;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chehaha.api.sms.ISmsServiceAdapter;
import com.chehaha.api.sms.chuanglan.request.SmsSendRequest;
import com.chehaha.api.sms.chuanglan.response.SmsSendResponse;
import com.chehaha.api.sms.chuanglan.util.ChuangLanSmsUtil;
import com.chehaha.common.exception.FrameworkException;
import com.chehaha.common.util.json.impl.FastjsonUtil;

/**   
* @Title: ChuangLanSmsServiceImpl.java 
* @Description: TODO(用一句话描述该文件做什么) 
* @author James.Wong
* @date 2018年2月9日 
* @version V1.0   
*/
@Service("chuanglan")
public class ChuangLanSmsServiceImpl implements ISmsServiceAdapter {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ChuangLanSmsConfig config;
	
	@Override
	public void send(String mobile, String text) throws FrameworkException {
		// TODO Auto-generated method stub
		final String report = "true";
		SmsSendRequest request = new SmsSendRequest(config.getAccount(), config.getSecret(), text, mobile,report);
		String responseValue = ChuangLanSmsUtil.sendSmsByPost(config.getApi(), FastjsonUtil.parse(request));
		SmsSendResponse response = FastjsonUtil.toObject(responseValue, SmsSendResponse.class);
		if(!"0".equals(response.getCode())){
			throw new FrameworkException(F7.SMS_SEND_ERROR,mobile+"::"+text,response.getCode(),response.getErrorMsg());
		}
	}

	@Override
	public void send(String mobile, String template, String... args) throws FrameworkException {
		// TODO Auto-generated method stub
		
	}

}
