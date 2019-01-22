package com.chehaha.api.payment.wechat.pojo.gongzhonghao.request;

import com.chehaha.api.payment.wechat.WeChatPayConfig;
import com.chehaha.api.wechat.WeChatConfig;

/**
 * @author DK
 * @date 2018/7/9 16:23
 */
public class UserAccessTokenParam {
    private String appid;//	是	公众号的唯一标识
    private String secret;//	是	公众号的appsecret
    private String code;//	是	填写第一步获取的code参数
    private String grant_type="authorization_code";//	是	填写为authorization_code



    public UserAccessTokenParam(String code, WeChatConfig config){
        this.code=code;
        this.appid=config.getAppid();
        this.secret=config.getAppsecret();
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
