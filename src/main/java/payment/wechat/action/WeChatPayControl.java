package com.chehaha.api.payment.wechat.action;

import com.chehaha.api.payment.IPaymentAdapter;
import com.chehaha.api.payment.wechat.api.WeChatPayApi;
import com.chehaha.api.wechat.WeChatApi;
import com.chehaha.api.wechat.WeChatConfig;
import com.chehaha.api.wechat.pojo.JsSdkInfo;
import com.chehaha.api.wechat.pojo.response.UserBaseInfoResponse;
import com.chehaha.common.action.BaseController;
import com.chehaha.common.action.pojo.RestResult;
import com.chehaha.common.exception.BizException;
import com.chehaha.common.exception.FrameworkException;
import com.chehaha.common.exception.code.F7;
import com.chehaha.common.util.string.StringUtil;
import com.chehaha.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

/**
 * Created by DK on 2018/1/7.
 */
@Controller
@RequestMapping("/wechat")
public class WeChatPayControl extends BaseController{
    @Autowired
    private IOrderService orderService;
    @Autowired
    @Qualifier("wechatpay")
    private IPaymentAdapter wechat;
    @Autowired
	private WeChatPayApi payApi;
    @Autowired
    private WeChatConfig config;

    @Autowired
    private WeChatApi api;


    @RequestMapping("/notify_url")
    @ResponseBody
    public String wechatNotifyUrl(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        String result ="";
        // PrintWriter pw = httpServletResponse.getWriter();
        try {
            InputStream inStream = httpServletRequest.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
             result = new String(outSteam.toByteArray(), "utf-8");
            //AlipayCore.logResult("XML:"+result);
            if (!StringUtil.isBlank(result)) {
                wechat.payCallback(result,httpServletRequest);
            }
        }catch (BizException e){
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  new FrameworkException(F7.WECHAT_CALLBACK_SIGN_ERROR,result,e.getMessage());
        }
        return payApi.notifySuccessXml() ;
    }



//    @RequestMapping("/bind")
//    @ResponseBody
//    public  String heartbeat(HttpServletRequest request){
//        String signature = request.getParameter("signature"); // 微信加密签名
//        String timestamp = request.getParameter("timestamp"); // 时间戳
//        String nonce = request.getParameter("nonce");// 随机数
//        String echostr = request.getParameter("echostr");// 随机字符串
//        if(api.checkSignature(signature,timestamp,nonce)){
//            return echostr;
//        }else {
//            return null;
//        }
//    }

    @RequestMapping("/jsapi/signature")
    @ResponseBody
    public ResponseEntity<RestResult<JsSdkInfo>> getJsApiSignature(String url) throws NoSuchAlgorithmException {
        return success(new JsSdkInfo(config.getAppid(),url,api.getJsapiTicket()));
    }

    @GetMapping("/user/baseinfo")
    @ResponseBody
    public ResponseEntity<RestResult<UserBaseInfoResponse>> getUserBaseInfo(String code, String openid){
        if(!StringUtil.isBlank(openid)){
            return success(api.getUserBaseInfoByOpenId(openid));
        }
        if(!StringUtil.isBlank(code)){
            return success(api.getUserBaseInfoByCode(code));
        }
        return success(new UserBaseInfoResponse());
    }
}
