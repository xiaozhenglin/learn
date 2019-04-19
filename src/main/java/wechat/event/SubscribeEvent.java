package com.chehaha.api.wechat.event;

import org.springframework.context.ApplicationEvent;
import scala.App;

/**
 * @author DK
 * @date 2018/8/20 22:45
 */
public class SubscribeEvent extends ApplicationEvent {
    private String openId;
    private boolean attention;
    public SubscribeEvent(String openId,boolean attention) {
        super(SubscribeEvent.class);
        this.openId=openId;
        this.attention=attention;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public boolean isAttention() {
        return attention;
    }

    public void setAttention(boolean attention) {
        this.attention = attention;
    }
}
