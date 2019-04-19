package com.chehaha.api.payment.wechat.pojo.gongzhonghao;

import com.chehaha.api.payment.wechat.api.WeChatPayApi;
import com.chehaha.common.util.string.EncryptionUtil;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

/**
 * @author DK
 * @date 2018/5/29 17:07
 */
public class JsSdkInfo implements Serializable{
    String appId;
    long timestamp=System.currentTimeMillis()/1000L;
    String nonceStr= WeChatPayApi.create_nonce_str();
    String signature;


    public JsSdkInfo(String appId,String url,String ticket) throws NoSuchAlgorithmException {
        this.appId = appId;
        if(ticket != null){
            String waitSign = "jsapi_ticket="+ticket+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url="+url;
            signature= EncryptionUtil.getSHA1(waitSign).toLowerCase();
        }
    }



    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
