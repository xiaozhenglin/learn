package com.chehaha.api.wechat.pojo.request;

import com.chehaha.api.payment.wechat.WeChatPayConfig;
import com.chehaha.api.wechat.WeChatConfig;

/**
 * @author DK
 * @date 2018/5/29 12:03
 */
public class AccessTokenParam {
    private String grant_type="client_credential";
    private String appid;
    private String secret;


    public AccessTokenParam(WeChatConfig config){
        this.appid=config.getAppid();
        this.secret=config.getAppsecret();
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
