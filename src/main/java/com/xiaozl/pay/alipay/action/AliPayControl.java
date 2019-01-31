package com.xiaozl.pay.alipay.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaozl.pay.alipay.IPaymentAdapter;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DK on 2017/12/22.
 */
@RestController
@RequestMapping("/alipay")
public class AliPayControl {
//    @Autowired
//    IAliPayService aliPayServiceImpl;

    @Autowired
    @Qualifier("alipay")
    IPaymentAdapter alipay;

    /**
     * 支付宝异步通知回调接口
     *
     * @param aliPayCallBackResponse
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/notify_url")
    public String aliPayCallBack(AliPayCallBackResponse aliPayCallBackResponse, HttpServletRequest httpServletRequest) {
//        aliPayServiceImpl.aliPayCallBack(aliPayCallBackResponse, httpServletRequest);
        alipay.payCallback(aliPayCallBackResponse,httpServletRequest);
        return "success";
    }
}
