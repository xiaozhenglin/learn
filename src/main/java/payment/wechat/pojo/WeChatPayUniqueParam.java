package com.chehaha.api.payment.wechat.pojo;

import com.chehaha.api.payment.pojo.IPayChannelUniqueParam;

/**
 * @author DK
 * @date 2018/3/13 18:54
 */
public class WeChatPayUniqueParam implements IPayChannelUniqueParam {
    private  String ip;
    private  String openId;

    public WeChatPayUniqueParam() {
    }

    public WeChatPayUniqueParam(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
