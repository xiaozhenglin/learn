package com.chehaha.api.wechat;

import com.chehaha.api.payment.wechat.Const;
import com.chehaha.api.payment.wechat.WeChatPayConfig;
import com.chehaha.api.wechat.pojo.request.*;
import com.chehaha.api.wechat.pojo.response.*;
import com.chehaha.common.exception.FrameworkException;
import com.chehaha.common.exception.code.F7;
import com.chehaha.common.type.ExpiredStrategy;
import com.chehaha.common.type.Section;
import com.chehaha.common.util.cache.RedisUtil;
import com.chehaha.common.util.http.RestUtil;
import com.chehaha.common.util.json.impl.FastjsonUtil;
import com.chehaha.common.util.string.EncryptionUtil;
import com.chehaha.message.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author DK
 * @date 2018/7/23 10:29
 */
@Service
public class WeChatApi {
    @Autowired
    private WeChatConfig config;

    /**
     * 绑定微信验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{config.getToken(), timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        String tmpStr = null;
        try {
            tmpStr = EncryptionUtil.getSHA1(content.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return  false;
        }
        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }


    public String getAccessToken() {
        String token = RedisUtil.get(Section.Wechat, Const.ACCESS_TOKEN);
        if (token != null) {
            return token;
        } else {
            String accessToken = refreshAccessToken();
            RedisUtil.save(Section.Wechat, Const.ACCESS_TOKEN, accessToken, ExpiredStrategy.THIRTY_MINUTES);
            return accessToken;
        }
    }

    public String refreshAccessToken(int retryTimes) {
        return refreshAccessToken(0, retryTimes);
    }

    public String refreshAccessToken() {
        return refreshAccessToken(3);
    }

    /**
     * 获取accessToken
     * <p>有重试机制,当前是第{@code currentTimes}次, 总共重试{@code retryTimes}次</p>
     *
     * @param currentTimes 当前次数
     * @param retryTimes   重试次数
     * @return
     */
    private String refreshAccessToken(int currentTimes, int retryTimes) {
        AccessTokenParam param = new AccessTokenParam(config);
        ResponseEntity<AccessTokenResponse> forEntity = RestUtil.getForEntity(config.getAccessTokenApi(), AccessTokenResponse.class, param);
        if (forEntity.getStatusCodeValue() == 200 && forEntity.getBody().isSuccess()) {
            String accessToken = forEntity.getBody().getAccess_token();
            return accessToken;
        } else {
            if (currentTimes >= retryTimes) {
                throw new FrameworkException(F7.GET_WECHAT_SERVICE_INFO_FAIL, Const.ACCESS_TOKEN, config.getAccessTokenApi(), FastjsonUtil.parse(param), FastjsonUtil.parse(forEntity));
            }
            return refreshAccessToken(++currentTimes, retryTimes);
        }
    }

    public String getJsapiTicket() {
        String ticket = RedisUtil.get(Section.Wechat, Const.JSAPI_TICKET);
        if (ticket != null) {
            return ticket;
        } else {
            ticket = refreshJsapiTicket();
            RedisUtil.save(Section.Wechat, Const.JSAPI_TICKET, ticket, ExpiredStrategy.THIRTY_MINUTES);
            return ticket;
        }
    }


    public String refreshJsapiTicket() {
        return refreshJsapiTicket(3);
    }

    public String refreshJsapiTicket(int retryTimes) {
        return refreshJsapiTicket(0, retryTimes);
    }

    /**
     * 获取JsapiTicket
     * <p>有重试机制,当前是第{@code currentTimes}次, 总共重试{@code retryTimes}次</p>
     *
     * @param currentTimes 当前次数
     * @param retryTimes   重试次数
     * @return
     */
    private String refreshJsapiTicket(int currentTimes, int retryTimes) {
        JsapiTicketParam jsapiTicketParam = new JsapiTicketParam(getAccessToken());
        ResponseEntity<JsapiTicketResponse> forEntity = RestUtil.getForEntity(config.getJsapiTicketApi(), JsapiTicketResponse.class, jsapiTicketParam);
        if (forEntity.getStatusCodeValue() == 200 && forEntity.getBody().isSuccess()) {
            String ticket = forEntity.getBody().getTicket();
            return ticket;
        } else {
            if(currentTimes >= retryTimes){
                throw new FrameworkException(F7.GET_WECHAT_SERVICE_INFO_FAIL, Const.ACCESS_TOKEN,config.getJsapiTicketApi(),FastjsonUtil.parse(jsapiTicketParam),FastjsonUtil.parse(forEntity));
            }
            return refreshJsapiTicket(++currentTimes, retryTimes);
        }
    }

    /**
     * 设置公众号菜单
     *
     * @return
     */
    public ResponseEntity<BaseResponse> setWechatMuen() {
        final String muen = " {\n" +
                "     \"button\":[\n" +
                "     {\t\n" +
                "          \"type\":\"view\",\n" +
                "          \"name\":\"车主服务\",\n" +
                "          \"url\":\"https://customer.chehaha.net/index.html\"\n" +
                "      },\n" +
                "      {\n" +
                "           \"name\":\"智车生活\",\n" +
                "           \"sub_button\":[\n" +
                "           {\t\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"OBD购买\",\n" +
                "               \"url\":\"http://mp.weixin.qq.com/bizmall/malldetail?id=&pid=pQydfv6nSEKO7_ohaTHSKDesu9Tw&biz=MzIxMDY4NDIyNw==&scene=&action=show_detail&showwxpaytitle=1#wechat_redirect\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"用车宝典\",\n" +
                "               \"url\":\"https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzIxMDY4NDIyNw==&scene=124#wechat_redirect\"\n" +
                "            }]\n" +
                "       },\n" +
                "\t   {\n" +
                "           \"name\":\"公司介绍\",\n" +
                "           \"sub_button\":[\n" +
                "           {\t\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"公司介绍\",\n" +
                "               \"url\":\"https://www.chehaha.net/\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"招贤纳士\",\n" +
                "               \"url\":\"http://www.chehaha.net/recruit/\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"平台模式\",\n" +
                "               \"url\":\"https://www.chehaha.net/league.html\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"APP下载\",\n" +
                "               \"url\":\"http://sj.qq.com/myapp/detail.htm?apkName=com.chehaha.app\"\n" +
                "            }]\n" +
                "       }]\n" +
                " }";
        ResponseEntity responseEntity =  RestUtil.postJsonForEntity(config.getCreateMuenApi() + "?access_token=" + getAccessToken(),muen,BaseResponse.class);
        System.out.println(FastjsonUtil.parse(responseEntity.getBody()));
        return responseEntity;
    }


    public UserAccessTokenResponse getUserAccessToken(String code) {
        UserAccessTokenParam param = new UserAccessTokenParam(code, config);
        ResponseEntity<UserAccessTokenResponse> forEntity = RestUtil.getForEntity(config.getUserAccessTokenApi(), UserAccessTokenResponse.class, param);
        if (forEntity.getStatusCodeValue() == 200 && forEntity.getBody().isSuccess()) {
            return forEntity.getBody();
        } else {
            throw new FrameworkException(F7.GET_WECHAT_SERVICE_INFO_FAIL, Const.USER_ACCESS_TOKEN, config.getUserAccessTokenApi(), FastjsonUtil.parse(param), FastjsonUtil.parse(forEntity));
        }
    }

    public UserBaseInfoResponse getUserBaseInfoByCode(String code) {
        UserAccessTokenResponse userAccessToken = getUserAccessToken(code);
        return getUserBaseInfoByOpenId(userAccessToken.getOpenid());
    }

    public UserBaseInfoResponse getUserBaseInfoByOpenId(String openId) {
        UserBaseInfoParam param = new UserBaseInfoParam(getAccessToken(), openId);
        ResponseEntity<UserBaseInfoResponse> forEntity = RestUtil.getForEntity(config.getUserBaseInfoApi(), UserBaseInfoResponse.class, param);
        if (forEntity.getStatusCodeValue() == 200 && forEntity.getBody().isSuccess()) {
            return forEntity.getBody();
        } else {
            throw new FrameworkException(F7.GET_WECHAT_SERVICE_INFO_FAIL, Const.USER_BASE_INFO,config.getUserBaseInfoApi(), FastjsonUtil.parse(param),FastjsonUtil.parse(forEntity));
        }
    }

    public  TemplateMessageResponse sendTemplateMessage(TemplateMessageParam param){
        ResponseEntity<TemplateMessageResponse> responseEntity =  RestUtil.postJsonForEntity(config.getSendTemplateMessage() + "?access_token=" + getAccessToken(),param,TemplateMessageResponse.class);
        if (responseEntity.getStatusCodeValue() == 200 && responseEntity.getBody().isSuccess()) {
            return responseEntity.getBody();
        }else {
            throw new FrameworkException(F7.GET_WECHAT_SERVICE_INFO_FAIL, Const.SEND_TEMPLATE_MESSAGE,config.getUserBaseInfoApi(), FastjsonUtil.parse(param),FastjsonUtil.parse(responseEntity));
        }
    }

}
