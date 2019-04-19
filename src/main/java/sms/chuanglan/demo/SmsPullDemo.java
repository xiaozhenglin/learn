package com.chehaha.api.sms.chuanglan.demo;

import java.io.UnsupportedEncodingException;

import com.chehaha.api.sms.chuanglan.request.SmsPullRequest;
import com.chehaha.api.sms.chuanglan.response.SmsPullResponse;
import com.chehaha.api.sms.chuanglan.util.ChuangLanSmsUtil;
import com.chehaha.common.util.json.impl.FastjsonUtil;

/**
 * @author tianyh
 * @Description:查询上行短信
 */
public class SmsPullDemo {

    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "";
    // 用户平台API密码(非登录密码)
    public static String pswd = "";

    public static void main(String[] args) throws UnsupportedEncodingException {

        //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
        String smsPullRequestUrl = "http://xxx/msg/pull/mo";
        //上行拉取条数
        String count = "1";

        SmsPullRequest smsPullRequest = new SmsPullRequest(account, pswd, count);

        String requestJson = FastjsonUtil.parse(smsPullRequest);

        System.out.println("before request string is: " + requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsPullRequestUrl, requestJson);

        System.out.println("response after request result is : " + response);

        SmsPullResponse smsPullResponse = FastjsonUtil.toObject(response, SmsPullResponse.class);

        System.out.println("response  toString is : " + smsPullResponse);
    }

}
