package com.chehaha.api.sms.chuanglan.demo;

import java.io.UnsupportedEncodingException;

import com.chehaha.api.sms.chuanglan.request.SmsBalanceRequest;
import com.chehaha.api.sms.chuanglan.response.SmsBalanceResponse;
import com.chehaha.api.sms.chuanglan.util.ChuangLanSmsUtil;
import com.chehaha.common.util.json.impl.FastjsonUtil;

/**
 * @author tianyh
 * @Description:查询余额
 */
public class SmsBalanceDemo {

    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "";
    // 用户平台API密码(非登录密码)
    public static String pswd = "";


    public static void main(String[] args) throws UnsupportedEncodingException {

        //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
        String smsBalanceRequestUrl = "http://xxx/msg/balance/json";

        SmsBalanceRequest smsBalanceRequest=new SmsBalanceRequest(account, pswd);

        String requestJson = FastjsonUtil.parse(smsBalanceRequest);

        System.out.println("before request string is: " + requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsBalanceRequestUrl, requestJson);

        System.out.println("response after request result is : " + response);

        SmsBalanceResponse smsVarableResponse = FastjsonUtil.toObject(response, SmsBalanceResponse.class);

        System.out.println("response  toString is : " + smsVarableResponse);


    }




}
