package com.chehaha.api.payment.wechat.pojo;

import com.chehaha.api.payment.wechat.WeChatPayConfig;
import com.chehaha.api.payment.wechat.api.WeChatPayApi;
import com.chehaha.common.util.json.impl.FastjsonUtil;

import java.io.Serializable;

/**
 *  * 关闭订单返查询对象
 * <P>参考自</P>
 * <URL>https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=9_3&index=3</URL>
 * @author DK
 * @date 2018/3/3 16:48
 */
public class WeChatCloseOrder implements Serializable {
    String appid;
    String mch_id;
    String out_trade_no;
    String nonce_str;
    String sign;


    public WeChatCloseOrder() {
    }

    public WeChatCloseOrder(String out_trade_no,WeChatPayConfig config) throws Exception {
        this.appid = config.getAppid();
        this.mch_id = config.getMchid();
        this.out_trade_no = out_trade_no;
        this.nonce_str = WeChatPayApi.create_nonce_str();;
        this.sign =  WeChatPayApi.generateSignature(FastjsonUtil.beanToMap(this),config.getKey(), WeChatPayConfig.SignType.MD5);;
    }

    public static WeChatCloseOrder instance(String tradeNo,WeChatPayConfig config) throws Exception {
        return new  WeChatCloseOrder(tradeNo,config);
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
