package com.chehaha.api.payment.wechat;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.chehaha.api.payment.pojo.PayApiType;

/**
 * Created by DK on 2017/12/23.
 */
@Component
@ConfigurationProperties(prefix = "third-party.pay.wechat")
public class WeChatPayConfig {

	private String appid;
	private String jsAppid;
	private String notifyUrl;
	private String key;
	private String mchid;
	private String sslCert;
	private String sslPassword;
	private final String unifiedorderApi = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 支付地址
	private final String refundApi = "https://api.mch.weixin.qq.com/secapi/pay/refund"; // 退款
	private final  String queryApi="https://api.mch.weixin.qq.com/pay/orderquery"; //交易查询
	private final  String closeApi="https://api.mch.weixin.qq.com/pay/closeorder"; //交易关闭
	private final String transfersApi="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers"; //转账



	public enum SignType {
		MD5, HMACSHA256
	}


	public  static PayApiType toPayApiType(String apiType){
		if (apiType == null){
			return null;
		}
		switch (apiType){
			case "APP":
				return PayApiType.APP;
			case "MWEB":
				return PayApiType.H5;
			case "JSAPI":
				return PayApiType.JSAPI;
			default:
				return null;
		}
	}

	public String getTradeType(PayApiType apiType) {
		switch (apiType) {
		case APP:
			return "APP";
		case H5:
			return "MWEB";
		case JSAPI:
			return "JSAPI";
		default:
			return null;
		}
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}

	public String getSslCert() {
		return sslCert;
	}

	public void setSslCert(String sslCert) {
		this.sslCert = sslCert;
	}

	public String getSslPassword() {
		return sslPassword;
	}

	public void setSslPassword(String sslPassword) {
		this.sslPassword = sslPassword;
	}

	public String getUnifiedorderApi() {
		return unifiedorderApi;
	}

	public String getRefundApi() {
		return refundApi;
	}

	public String getQueryApi() {
		return queryApi;
	}

	public String getCloseApi() {
		return closeApi;
	}

	public String getTransfersApi() {
		return transfersApi;
	}

	public String getJsAppid() {
		return jsAppid;
	}

	public void setJsAppid(String jsAppid) {
		this.jsAppid = jsAppid;
	}
}
