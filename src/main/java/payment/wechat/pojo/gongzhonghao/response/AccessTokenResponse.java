package com.chehaha.api.payment.wechat.pojo.gongzhonghao.response;

/**
 * @author DK
 * @date 2018/5/29 14:23
 */
public class AccessTokenResponse extends  BaseResponse {
    String access_token;
    int expires_in;


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
