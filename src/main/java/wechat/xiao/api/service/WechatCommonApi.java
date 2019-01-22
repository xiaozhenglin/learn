package com.chehaha.api.wechat.xiao.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.chehaha.common.exception.FrameworkException;
import com.chehaha.common.util.http.RestUtil;
import com.chehaha.api.wechat.xiao.api.WechatConfig;
import com.chehaha.api.wechat.xiao.api.pojo.WechatTokenResult;


public class WechatCommonApi {

	private final static Class<?> clazz = WechatCommonApi.class;
	
	/**
	 * 激活微信公众号的第三方签名认证
	 * @param request
	 * @return
	 * @throws FrameworkException
	 */
//	public static boolean checkWechatSignature(HttpServletRequest request) throws FrameworkException{
//		WechatSignatureInfo signInfo = new WechatSignatureInfo(request);
//		//判断是否来自微信服务器
//		if(signInfo.isComefromWechat()) {
//			String data[] = new String[] {WechatConfig.getCredential(), signInfo.getTimestamp(), signInfo.getNonce()};
//			//字典排序数组
//			Arrays.sort(data);
//			StringBuffer signValue = new StringBuffer();
//			for(int i=0;i<data.length;i++) {
//				signValue.append(data[i]);
//			}
//			//SHA加密生成签名
//			String value = SHA.encrypt(signValue.toString());
//			//比对签名
//			if(signInfo.getSignature().equals(value)) {
//				return true;
//			}
//		}
//		return false;
//	}
	
//	public static String refreshAccessToken() throws FrameworkException {
//		final String api = "https://api.weixin.qq.com/cgi-bin/token";
//
//		Map<String,String> param = new HashMap<>();
//		param.put("grant_type", "client_credential");
//		param.put("appid", WechatConfig.getAppId());
//		param.put("secret", WechatConfig.getSecret());
//
//		WechatTokenResult result = RestUtil.getForEntity(api, param,WechatTokenResult.class).getBody();
//		if(result.isSuccess()) {
//			return result.getToken();
//		}
//
//		throw result.getException(clazz);
//	}
	
	
//	public static String getTicket(WechatTicketType type) throws FrameworkException {
//		final String api = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
//		
//		Map<String,String> param = new HashMap<>();
//		param.put("access_token", WechatConfig.getAccessToken());
//		param.put("type", type.getValue());
//		
//		WechatTicketResult result = RestUtil.getForEntity(api, param,WechatTicketResult.class).getBody();
//		if (result.isTokenExpired()) {
//			return getTicket(type);
//		}
//		if(result.isSuccess()) {
//			return result.getTicket();
//		}
//		throw result.getException(clazz);
//	}
//	
//	public static JsSdkInfo getJsSdkInfo(String url) throws FrameworkException{
//		try {
//			final String jsTicket = WechatConfig.getJsTicket();
//			if(StringUtil.isEmpty(jsTicket)) {
//				throw new FrameworkException(ThirdpartyApiError.WECHAT_API_FAILED,"未获取到微信验证票据");
//			}
//			return new JsSdkInfo(URLDecoder.decode(url, "UTF-8"),jsTicket);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			throw new FrameworkException(ThirdpartyApiError.WECHAT_API_FAILED,"传入地址字符编码非UTF-8");
//		}
//	}
	
	
	
	
}
