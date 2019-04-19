package com.chehaha.api.payment.wechat.pojo;

import com.chehaha.api.payment.wechat.WeChatPayConfig;
import com.chehaha.api.payment.wechat.api.WeChatPayApi;
import com.chehaha.common.util.json.impl.FastjsonUtil;

import java.io.Serializable;

/**
 * 微信订单查询参数类
 * <p>参考自以下链接</p>
 * <url>https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_2&index=4</url>
 * @author DK
 * @date 2018/3/3 12:06
 */
public class WeChatTradeQuery implements Serializable {
    String appid;
    String mch_id;
    String out_trade_no;
    String nonce_str;
    String sign;

    public WeChatTradeQuery() {
    }

    public WeChatTradeQuery(String out_trade_no,WeChatPayConfig config,String appid) throws Exception {
        this.appid =appid;
        this.mch_id = config.getMchid();
        this.out_trade_no = out_trade_no;
        this.nonce_str = WeChatPayApi.create_nonce_str();;
        this.sign =  WeChatPayApi.generateSignature(FastjsonUtil.beanToMap(this),config.getKey(), WeChatPayConfig.SignType.MD5);;
    }

    public static WeChatTradeQuery instance(String tradeNo,WeChatPayConfig config,String appid) throws Exception {
        return new  WeChatTradeQuery(tradeNo,config,appid);
    }


    public String toXml(){
        return "<xml>" +
                "   <appid>"+appid+"</appid>" +
                "   <mch_id>"+mch_id+"</mch_id>" +
                "   <nonce_str>"+nonce_str+"</nonce_str>" +
                "   <out_trade_no>"+out_trade_no+"</out_trade_no>" +
                "   <sign>"+sign+"</sign>" +
                "</xml>";
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

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
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
}
