package com.chehaha.api.wechat.xiao.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.converter.HttpMessageConverter;

import com.chehaha.common.util.http.RestUtil;
import com.chehaha.api.wechat.xiao.api.WechatConfig;
import com.chehaha.api.wechat.xiao.api.pojo.user.WechatUser;

import net.sf.json.JSONObject;

public class WechatUserApi {

	private final static Class<?> clazz = WechatUserApi.class;
	
//	public static boolean check(String oAuthToken,String openId) throws FrameworkException{
//		final String api = "https://api.weixin.qq.com/sns/auth";
//		
//		Map<String,String> param = new HashMap<>();
//		param.put("access_token", oAuthToken);
//		param.put("openid", openId);
//		
//		WechatApiResult result = RestUtil.getForEntity(api, param,WechatApiResult.class).getBody();
//		if(result.isSuccess()) {
//			return true;
//		}
//		throw result.getException(clazz);
//	}
	
//	public static WechatOAuth2Result getOAuthToken(String code) throws FrameworkException{
//		final String api = "https://api.weixin.qq.com/sns/oauth2/access_token";
//		
//		Map<String,String> param = new HashMap<>();
//		param.put("appid", WechatConfig.getAppId());
//		param.put("secret", WechatConfig.getSecret());
//		param.put("code", code);
//		param.put("grant_type", "authorization_code");
//		HttpMessageConverter<?> converter = new WechatMappingJackson2HttpMessageConverter();
//		WechatOAuth2Result result = RestUtil.getJsonForEntity(api, param,WechatOAuth2Result.class,converter).getBody();
//		if(result.isSuccess()) {
//			return result;
//		}
//		throw result.getException(clazz);
//	}

//	public static WechatOAuth2Result refreshOAuthToken(String refreshToken) throws FrameworkException{
//		final String api = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
//		
//		Map<String,String> param = new HashMap<>();
//		param.put("appid", WechatConfig.getAppId());
//		param.put("refresh_token", refreshToken);
//		param.put("grant_type", "refresh_token");
//		
//		WechatOAuth2Result result = RestUtil.getForEntity(api, param,WechatOAuth2Result.class).getBody();
//		if(result.isSuccess()) {
//			return result;
//		}
//		throw result.getException(clazz);
//	}
	
//	public static String getOpenId(String code) throws FrameworkException{
//		return getOAuthToken(code).getOpenId();
//	}
//
//	public static WechatUser getInfo(String code) throws FrameworkException{
//		 WechatOAuth2Result auth = getOAuthToken(code);
//		 return getInfo(auth.getOauthToken(),auth.getOpenId());
//	}
//	
//	public static SubScribleWechatUser getSubScribleInfo(String code) throws FrameworkException{
//		 WechatOAuth2Result auth = getOAuthToken(code);
//		 WechatUser user = getInfo(auth.getOauthToken(),auth.getOpenId());
//		 boolean isSubScrible = isSubScrible(auth.getOpenId());
//		 return new SubScribleWechatUser(user,isSubScrible);
//	}
//	
//	public static WechatUser getInfo(String oAuthToken,String openId) throws FrameworkException{
//		final String api = "https://api.weixin.qq.com/sns/userinfo";
//		
//		Map<String,String> param = new HashMap<>();
//		param.put("access_token", oAuthToken);
//		param.put("openid", openId);
//		param.put("lang", "zh_CN");
//
//		WechatUser result = RestUtil.getForEntity(api, param,WechatUser.class).getBody();
//		if(result.isSuccess()) {
//			return result;
//		}
//		throw result.getException(clazz);
//	}
//	
//	public static boolean isSubScrible(String openId) {
////		String token = WechatConfig.getAccessToken(); 
//		//拉取用户信息
//		final String api = "https://api.weixin.qq.com/cgi-bin/user/info";
//		
//		Map<String,String> param = new HashMap<>();
////		param.put("access_token", token);
//		param.put("access_token", WechatConfig.getAccessToken());
//		param.put("openid", openId);
//		param.put("lang", "zh_CN");
//		
//		SimpleSubScrible result = RestUtil.getForEntity(api, param,SimpleSubScrible.class).getBody();
//		if(result.isSuccess()) {
//			return result.isSubscribe();
//		}
//		throw result.getException(clazz);
//	}
	
	//获取用户信息
	public static WechatUser getWechatUserInfo(String openId) {
	final String api = "https://api.weixin.qq.com/cgi-bin/user/info";
		
		Map<String,String> param = new HashMap<>();
//		param.put("access_token", token);
		param.put("access_token", WechatConfig.getAccessToken());
		param.put("openid", openId);
		param.put("lang", "zh_CN");
		WechatUser result = RestUtil.getForEntity(api, param,WechatUser.class).getBody();
		if(result.isSuccess()) {
			return result;
		}
		throw result.getException(clazz);
	}
	
}
