package com.chehaha.api.wechat.xiao.api;

import java.io.Serializable;

import com.chehaha.common.exception.FrameworkException;
import com.chehaha.common.exception.code.F7;
import com.chehaha.common.util.string.StringUtil;
import com.chehaha.api.wechat.xiao.api.exception.CustomerServiceException;
import com.chehaha.api.wechat.xiao.api.exception.WechatBusyException;
import com.chehaha.api.wechat.xiao.api.exception.WechatMenuException;
import com.chehaha.api.wechat.xiao.api.exception.WechatUserException;
import com.chehaha.api.wechat.xiao.api.service.CustomerServiceApi;
import com.chehaha.api.wechat.xiao.api.service.WechatMenuApi;
import com.chehaha.api.wechat.xiao.api.service.WechatUserApi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WechatApiResult implements Serializable {


	private static final long serialVersionUID = -1278040726879550292L;
	@JsonProperty("errcode")
	protected String errorCode;
	@JsonProperty("errmsg")
	protected String errorMessage;
	
	
	
	public boolean isSuccess() {
		return "0".equals(errorCode);
	}
	
	public boolean isBusy() {
		return "-1".equals(errorCode);
	}
	
	public RuntimeException getException(Class<?> apiClass) {		
		
		if(isBusy()) {
			return new WechatBusyException();
		}else {
			String apiError = "";
			if(apiClass.isAssignableFrom(CustomerServiceApi.class)) {
				apiError = new CustomerServiceException().get(errorCode);
			}else if(apiClass.isAssignableFrom(WechatMenuApi.class)) {
				apiError = new WechatMenuException().get(errorCode);
			}else if(apiClass.isAssignableFrom(WechatUserApi.class)) {
				apiError = new WechatUserException().get(errorCode);
			}
//			else if(apiClass.isAssignableFrom(TemplateMessageApi.class)) {
//				apiError = new WechatTemplateException().get(errorCode);
//			}
			if(StringUtil.isBlank(apiError)) {
				apiError = "微信异常未定义";
			}
			
//			return new FrameworkException(ThirdpartyApiError.WECHAT_API_FAILED,apiError,errorMessage);
			return new FrameworkException(F7.GET_WECHAT_SERVICE_INFO_FAIL,"","",apiError,errorMessage);
		}
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
