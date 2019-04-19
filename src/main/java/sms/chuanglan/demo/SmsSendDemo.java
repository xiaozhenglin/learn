package com.chehaha.api.sms.chuanglan.demo;

import java.io.UnsupportedEncodingException;

import com.chehaha.api.sms.chuanglan.request.SmsSendRequest;
import com.chehaha.api.sms.chuanglan.response.SmsSendResponse;
import com.chehaha.api.sms.chuanglan.util.ChuangLanSmsUtil;
import com.chehaha.common.util.json.impl.FastjsonUtil;

/**
 *
 * @author tianyh
 * @Description:普通短信发送
 */
public class SmsSendDemo {

    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "N3017642";
    // 用户平台API密码(非登录密码)
    public static String pswd = "w4Duy9SItY583f";

    public static void main(String[] args) throws UnsupportedEncodingException {

        //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
        String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
        // 短信内容
        String msg = "xxxxxx";
        //手机号码
        String phone = "18673113475";
        //状态报告
        String report= "true";

        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, phone,report);

        String requestJson = FastjsonUtil.parse(smsSingleRequest);

        System.out.println("before request string is: " + requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);

        System.out.println("response after request result is :" + response);

        SmsSendResponse smsSingleResponse = FastjsonUtil.toObject(response, SmsSendResponse.class);

        System.out.println("response  toString is :" + smsSingleResponse);


    }


}
