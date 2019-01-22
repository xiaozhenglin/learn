package com.chehaha.api.wechat.pojo.request;

/**
 * @author DK
 * @date 2018/5/29 14:44
 */
public class JsapiTicketParam {
    String access_token;
    String type="jsapi";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsapiTicketParam(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
