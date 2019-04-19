package com.chehaha.api.wechat;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author DK
 * @date 2018/8/2 15:25
 */
@Component
public class WeChatConfig {
    @Value("${third-party.wechat.appid}")
    private String appid;
    @Value("${third-party.wechat.token}")
    private String token;
    @Value("${third-party.wechat.appsecret}")
    private String appsecret;

    @Value("${third-party.wechat.wechatId}")
    private String wechatId;

    @Value("${third-party.wechat.suffix}")
    private String suffix;

    @Value("${third-party.wechat.template.order}")
    private String orderTemplate;

    @Value("${third-party.wechat.template.custservice}")
    private String custServiceTemplate;

    private final String accessTokenApi="https://api.weixin.qq.com/cgi-bin/token";//获取access token 地址
    private final String jsapiTicketApi="https://api.weixin.qq.com/cgi-bin/ticket/getticket";//获取JsapiTicket 地址
    private final String createMuenApi="https://api.weixin.qq.com/cgi-bin/menu/create"; //创建微信菜单
    private final String userAccessTokenApi ="https://api.weixin.qq.com/sns/oauth2/access_token"; //获取用户token
    private final String userBaseInfoApi ="https://api.weixin.qq.com/cgi-bin/user/info"; //获取用户基本信息
    private final String sendTemplateMessage="https://api.weixin.qq.com/cgi-bin/message/template/send"; //发送模板消息


    public String getAppid() {
        return appid;
    }

    public String getToken() {
        return token;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public String getAccessTokenApi() {
        return accessTokenApi;
    }

    public String getJsapiTicketApi() {
        return jsapiTicketApi;
    }

    public String getCreateMuenApi() {
        return createMuenApi;
    }

    public String getUserAccessTokenApi() {
        return userAccessTokenApi;
    }

    public String getUserBaseInfoApi() {
        return userBaseInfoApi;
    }

    public String getSendTemplateMessage() {
        return sendTemplateMessage;
    }

    public String getOrderTemplate() {
        return orderTemplate;
    }

    public String getCustServiceTemplate() {
        return custServiceTemplate;
    }


    public String getWechatId() {
        return wechatId;
    }

    public String getSuffix() {
        return suffix;
    }
}
