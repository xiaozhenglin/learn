package com.chehaha.api.payment.wechat.pojo;

import com.chehaha.api.payment.pojo.ITransferChannelUniqueParam;
import com.chehaha.api.payment.pojo.ITransferParam;
import com.chehaha.api.payment.wechat.WeChatPayConfig;
import com.chehaha.api.payment.wechat.api.WeChatPayApi;
import com.chehaha.common.util.json.impl.FastjsonUtil;

import java.io.Serializable;

/**
 * @author DK
 * @date 2018/3/15 19:31
 */
public class WeChatTransfersParam implements Serializable{

    private String mch_appid;//微信开放平台审核通过的应用APPID
    private String mchid;//微信支付分配的商户号
    private String nonce_str;//随机字符串，不长于32位。推荐随机数生成算法
    private String sign;//签名
    private String partner_trade_no;//商户订单号 String(32)	1217752501201407033233368018	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    private String openid; //商户appid下，某用户的openid
    private String check_name="NO_CHECK"; //NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名
    private String amount;//企业付款金额，单位为分
    private String desc; //企业付款操作说明信息。必填。
    private String spbill_create_ip; //该IP同在商户平台设置的IP白名单中的IP没有关联，该IP可传用户端或者服务端的IP。

    public WeChatTransfersParam(WeChatPayConfig config, ITransferParam transfer) throws Exception {
        this.mch_appid = config.getAppid();//WeChatPayConfig.APP_ID;
        this.mchid = config.getMchid();//WeChatPayConfig.MCH_ID;
        this.nonce_str = WeChatPayApi.create_nonce_str();
        this.partner_trade_no =transfer.getCode();
        this.openid = transfer.getRivalAccount();
        this.amount = calculationAmount(transfer.getAmount());
        this.desc = transfer.getRemark();
        WechatTransferUniqueParam wechatTransferUniqueParam = (WechatTransferUniqueParam) transfer.getTransferChannelUniqueParam();
        this.spbill_create_ip = wechatTransferUniqueParam.getIp();
        this.sign = WeChatPayApi.generateSignature(FastjsonUtil.beanToMap(this),config.getKey(),WeChatPayConfig.SignType.MD5);
    }

    private  String  calculationAmount(String amount){
        double v = Double.parseDouble(amount) * 100;
        int a = (int) v;
        return  a+"";
    }


    public  String toXml(){
        return "<xml>"
                +"<mch_appid>"+mch_appid+"</mch_appid>" +
                "<mchid>"+mchid+"</mchid>" +
                "<nonce_str>"+nonce_str+"</nonce_str>" +
                "<partner_trade_no>"+partner_trade_no+"</partner_trade_no>" +
                "<openid>"+openid+"</openid>" +
                "<check_name>"+check_name+"</check_name>" +
                "<amount>"+amount+"</amount>" +
                "<desc>"+desc+"</desc>" +
                "<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>" +
                "<sign>"+sign+"</sign>" +
                "</xml>";
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

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCheck_name() {
        return check_name;
    }

    public void setCheck_name(String check_name) {
        this.check_name = check_name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }
}
