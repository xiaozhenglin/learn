package com.chehaha.api.payment.wechat.pojo.gongzhonghao.response;

/**
 * @author DK
 * @date 2018/5/29 15:00
 */
public class JsapiTicketResponse extends BaseResponse {
    String ticket;
    int expires_in;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
