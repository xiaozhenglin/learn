package com.chehaha.api.payment.wechat.pojo.gongzhonghao.request;

import com.chehaha.api.payment.wechat.WeChatPayConfig;

/**
 * @author DK
 * @date 2018/7/9 16:40
 */
public class UserBaseInfoParam {
    private String access_token;
    private String openid;
    private String lang="zh_CN";


    public UserBaseInfoParam(String  accessToken,String openid){
        this.openid=openid;
        this.access_token=accessToken;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}