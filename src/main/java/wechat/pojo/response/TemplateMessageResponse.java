package com.chehaha.api.wechat.pojo.response;

/**
 * @author DK
 * @date 2018/7/25 10:49
 */
public class TemplateMessageResponse extends BaseResponse {
    private String msgid;


    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}
