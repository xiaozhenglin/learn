package com.chehaha.api.payment.wechat.pojo;

import com.chehaha.api.payment.pojo.ITransferChannelUniqueParam;

/**
 * @author DK
 * @date 2018/3/15 19:41
 */
public class WechatTransferUniqueParam implements ITransferChannelUniqueParam {
    private  String ip;

    public WechatTransferUniqueParam() {
    }

    public WechatTransferUniqueParam(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}