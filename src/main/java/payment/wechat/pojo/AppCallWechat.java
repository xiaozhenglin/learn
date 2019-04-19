package com.chehaha.api.payment.wechat.pojo;

import com.chehaha.api.payment.wechat.WeChatPayConfig;
import com.chehaha.api.payment.wechat.api.WeChatPayApi;
import com.chehaha.api.wechat.xiao.api.WechatConfig;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DK on 2017/12/12.
 */
public class AppCallWechat implements Serializable {
    String appid;
    String noncestr;
    String package_str;
    String partnerid;
    String prepayid;
    String timestamp;
    String sign;
    String signType="MD5";
    private String mweb_url; //H5 调用地址

    public AppCallWechat(PrepayIdResponse prepayIdResponse,String key) {
        this.appid = prepayIdResponse.getAppid();
        this.noncestr = prepayIdResponse.getNonce_str();
        this.partnerid = prepayIdResponse.getMch_id();
        this.prepayid = prepayIdResponse.getPrepay_id();
        String timestamp = (new Date().getTime() + "").substring(0, 10);
        this.timestamp = timestamp;

        switch (WeChatPayConfig.toPayApiType(prepayIdResponse.getTrade_type())){
            case APP:
            case H5:
                this.package_str = "Sign=WXPay";
                this.sign = getAPPSign(key);
                this.mweb_url = prepayIdResponse.getMweb_url();
                break;
            case JSAPI:
                this.package_str = "prepay_id="+prepayIdResponse.getPrepay_id();
                this.sign=getJSAPISign(key);
                break;

        }



    }
    private String getAPPSign(String key){
       return  WeChatPayApi.string2MD5("appid="+appid+"&noncestr="+noncestr+"&package="+package_str+"&partnerid="+partnerid
               +"&prepayid="+prepayid+"&timestamp="+timestamp+"&key="+key).toUpperCase();
    }

    private String getJSAPISign(String key){
        return  WeChatPayApi.string2MD5("appId="+appid+"&nonceStr="+noncestr+"&package="+package_str+"&signType="+signType+"&timeStamp="+timestamp+"&key="+key);
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackage_str() {
        return package_str;
    }

    public void setPackage_str(String package_str) {
        this.package_str = package_str;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMweb_url() {
        return mweb_url;
    }

    public void setMweb_url(String mweb_url) {
        this.mweb_url = mweb_url;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
