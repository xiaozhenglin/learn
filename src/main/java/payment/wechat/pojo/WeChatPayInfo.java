package com.chehaha.api.payment.wechat.pojo;

import com.chehaha.api.payment.pojo.PayApiType;
import com.chehaha.api.payment.wechat.WeChatPayConfig;
import com.chehaha.common.util.string.StringUtil;

import java.io.Serializable;
import java.util.Date;

public class WeChatPayInfo implements Serializable{
	 /**
	 * 
	 */
	 private static final long serialVersionUID = -2147939116622111815L;
	 private String appid;//微信开放平台审核通过的应用APPID
	 private String mch_id;//微信支付分配的商户号
	 private String device_info;//终端设备号(门店号或收银设备ID)，默认请传"WEB"
	 private String nonce_str;//随机字符串，不长于32位。推荐随机数生成算法
	 //private String nonce_str="5K8264ILTKCH16CQ2502SI8ZNMTM67VS";//随机字符串，不长于32位。推荐随机数生成算法
	 private String sign;//签名
	 private String body;//商品或支付单简要描述
	 private String attach;//附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	 private String out_trade_no;//客户端订单号
	 private String total_fee;//订单总价  单位  分
	 private String spbill_create_ip;//订单总价
	 private String notify_url;//接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
	 private String trade_type;//支付类型
	 private String openid;

	public WeChatPayInfo() {
	}


	public String transeXml(){
		StringBuffer ret=new StringBuffer("<xml>");
		ret.append("<appid>"+appid+"</appid>");
		ret.append("<attach>"+attach+"</attach>");
		ret.append("<body>"+body+"</body>");
		ret.append("<device_info>"+device_info+"</device_info>");
		ret.append("<mch_id>"+mch_id+"</mch_id>");
		ret.append("<nonce_str>"+nonce_str+"</nonce_str>");
		ret.append("<notify_url>"+notify_url+"</notify_url>");
		ret.append("<out_trade_no>"+out_trade_no+"</out_trade_no>");
		ret.append("<sign>"+sign+"</sign>");
		ret.append("<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>");
		ret.append("<total_fee>"+total_fee+""+"</total_fee>");
		ret.append("<trade_type>"+trade_type+"</trade_type>");
		if(StringUtil.isNotBlank(openid)){
			ret.append("<openid>"+openid+"</openid>");
		}
		ret.append("</xml>");
		return ret.toString();
	}
	 
	public String getAppid() {
		return appid;
	}

	public void setAppid(WeChatPayConfig config, PayApiType payApiType) {
		if(payApiType == PayApiType.JSAPI){
			this.appid = config.getJsAppid();
		}else {
			this.appid = config.getAppid();
		}

	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	 
}
