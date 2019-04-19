package com.chehaha.api.payment.wechat.pojo;

import com.chehaha.api.payment.wechat.WeChatPayConfig;
import com.chehaha.api.payment.wechat.WeChatPayConfig.SignType;
import com.chehaha.api.payment.wechat.api.WeChatPayApi;
import com.chehaha.common.util.json.impl.FastjsonUtil;

import java.io.Serializable;

/**
 * 微信退款参数
 * <p>参考自<url>https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4</url></p>
 * Created by DK on 2018/1/11.
 */
public class WeChatRefund implements Serializable {
    private static final long serialVersionUID = -344601031690226874L;

    private String appid;//微信开放平台审核通过的应用APPID
    private String mch_id;//微信支付分配的商户号
    private String nonce_str;//随机字符串，不长于32位。推荐随机数生成算法
    private String sign;//签名
    private String sign_type;//签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
    private String transaction_id;// 微信订单号 二选一	String(32)	1217752501201407033233368018	微信生成的订单号，在支付通知中有返回
    private String out_trade_no;//商户订单号 String(32)	1217752501201407033233368018	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    private String out_refund_no;//商户退款单号	是	String(64)	1217752501201407033233368018	商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
    private String total_fee;//订单金额	是	Int	100	订单总金额，单位为分，只能为整数，详见支付金额
    private String refund_fee;//退款金额	是	Int	100	退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
    private String refund_desc;//退款原因

    public WeChatRefund(WeChatCallBackResponse weChatCallBackResponse,String refund_desc,WeChatPayConfig config) throws Exception {
        this.appid = weChatCallBackResponse.getAppid();//WeChatPayConfig.APP_ID;
        this.mch_id = weChatCallBackResponse.getMch_id();//WeChatPayConfig.MCH_ID;
        this.nonce_str = WeChatPayApi.create_nonce_str();
        this.sign_type = SignType.MD5.toString();
        this.transaction_id = weChatCallBackResponse.getTransaction_id();
        this.out_trade_no = weChatCallBackResponse.out_trade_no;
        this.out_refund_no = weChatCallBackResponse.out_trade_no;
        this.total_fee = weChatCallBackResponse.getTotal_fee();
        this.refund_fee = weChatCallBackResponse.getTotal_fee();
        this.refund_desc = refund_desc;
        this.sign = WeChatPayApi.generateSignature(FastjsonUtil.beanToMap(this),config.getKey(),WeChatPayConfig.SignType.MD5);
    }

    public  static  WeChatRefund instance(WeChatCallBackResponse weChatCallBackResponse,String refund_desc,WeChatPayConfig config) throws Exception {
        return new WeChatRefund(weChatCallBackResponse,refund_desc,config);
    }

    public String toXml(){
        return "<xml>"
                +"<appid>"+this.appid+"</appid>"
                +"<mch_id>"+this.mch_id+"</mch_id>"
                +"<nonce_str>"+this.nonce_str+"</nonce_str>"
                +"<sign>"+this.sign+"</sign>"
                +"<sign_type>"+this.sign_type+"</sign_type>"
                +"<transaction_id>"+this.transaction_id+"</transaction_id>"
                +"<out_trade_no>"+this.out_trade_no+"</out_trade_no>"
                +"<out_refund_no>"+this.out_refund_no+"</out_refund_no>"
                +"<total_fee>"+this.total_fee+"</total_fee>"
                +"<refund_fee>"+this.refund_fee+"</refund_fee>"
                +"<refund_desc>"+this.refund_desc+"</refund_desc>"
                +"</xml>";
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
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

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getRefund_desc() {
        return refund_desc;
    }

    public void setRefund_desc(String refund_desc) {
        this.refund_desc = refund_desc;
    }


}
