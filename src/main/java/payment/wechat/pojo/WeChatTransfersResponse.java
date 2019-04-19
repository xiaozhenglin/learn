package com.chehaha.api.payment.wechat.pojo;

import com.chehaha.api.payment.wechat.Const;
import com.chehaha.common.util.string.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author DK
 * @date 2018/3/15 19:59
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeChatTransfersResponse implements Serializable {
    String return_code;//SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
    String return_msg;//返回信息，如非空，为错误原因
    String mch_appid;// 微信开放平台审核通过的应用APPID
    String mchid;//微信支付分配的商户号

    String device_info;//微信支付分配的终端设备号，

    String nonce_str;//随机字符串，不长于32位

    String result_code;//业务结果	 SUCCESS/FAIL

    String err_code;//错误返回的信息描述
    String err_code_des;//错误代码

    String partner_trade_no; //商户订单号

    String payment_no; //微信订单号

    String payment_time; //微信支付成功时间


    public boolean isSuccess(){
        return  StringUtil.equals(Const.SUCCESS,this.return_code) && StringUtil.equals(Const.SUCCESS,this.result_code);
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getMch_appid() {
        return mch_appid;
    }

    public void setMch_appid(String mch_appid) {
        this.mch_appid = mch_appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
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

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    public String getPayment_no() {
        return payment_no;
    }

    public void setPayment_no(String payment_no) {
        this.payment_no = payment_no;
    }

    public String getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(String payment_time) {
        this.payment_time = payment_time;
    }
}
