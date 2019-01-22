package com.chehaha.api.wechat.xiao.api;


import com.chehaha.api.wechat.WeChatConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chehaha.api.wechat.WeChatApi;
import com.chehaha.common.util.SpringUtil;

@Component
public class WechatConfig implements InitializingBean {
	@Autowired
	WeChatConfig  api;
	private static Logger log = LoggerFactory.getLogger(WechatConfig.class);
	
	private static String appId;
	private static String secret;
	private static String wechatId;
	private static String credential;

	private final static String accessTokenKey = "wechat.token";
	
	private final static String jsTicketKey = "wechat.jsapi";
	
	private static WechatCustomServiceConfig customService;

//	@Value("${third-party.pay.wechat.appid}")
//	public void setAppId(String appId) {
//		WechatConfig.appId = appId;
//	}

//	@Value("${third-party.pay.wechat.appsecret}")
//	public void setSecret(String secret) {
//		WechatConfig.secret = secret;
//	}

//	@Value("${third-party.pay.wechat.wechatId}")
//	public void setWechatId(String wechatId) {
//		WechatConfig.wechatId = wechatId;
//	}
//
//	@Value("${third-party.pay.wechat.token}")
//	public void setCredential(String credential) {
//		WechatConfig.credential = credential;
//	}
//
//	@Value("${third-party.pay.wechat.suffix}")
//	public void setCustomService(String suffix) {
//		WechatConfig.customService = new WechatCustomServiceConfig(suffix);
//	}

	public static String getAccessToken() {
		WeChatApi wechatApi = SpringUtil.getBean(WeChatApi.class);
		String accessToken = wechatApi.getAccessToken();
		return accessToken ;
//		if(StringUtil.isEmpty(token)) {
//			try {
//				token = WechatCommonApi.refreshAccessToken();
//			}catch (WechatBusyException ex) {
//				token = WechatCommonApi.refreshAccessToken();
//			}
//			RedisUtil.saveGlobal(accessTokenKey, token, ExpiredStrategy.ONE_HOUR);
//			log.info("微信AccessToken获取："+token);
//		}
//		return token;
		
	}

	public static String getAppId() {
		return appId;
	}

	public static String getSecret() {
		return secret;
	}

	public static String getWechatId() {
		return wechatId;
	}

	public static String getCredential() {
		return credential;
	}

	public static WechatCustomServiceConfig getCustomService() {
		return customService;
	}

	public static String getJsTicket() {
		return SpringUtil.getBean(WeChatApi.class).getJsapiTicket();
	}

	public static String getAccessTokenKey() {
		return accessTokenKey;
	}

	public static String getJsTicketKey() {
		return jsTicketKey;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		WechatConfig.appId = api.getAppid();
		WechatConfig.secret=api.getAppsecret();
		WechatConfig.wechatId=api.getWechatId();
		WechatConfig.credential=api.getToken();
		WechatConfig.customService= new WechatCustomServiceConfig(api.getSuffix());
	}
}
