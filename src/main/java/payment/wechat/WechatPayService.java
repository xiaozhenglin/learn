package com.chehaha.api.payment.wechat;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.chehaha.api.payment.pojo.*;
import com.chehaha.api.payment.wechat.pojo.*;
import com.chehaha.common.exception.code.F7;
import com.chehaha.common.exception.code.F8;
import com.chehaha.common.util.string.StringUtil;
import com.chehaha.fee.pojo.PayPipe;
import com.chehaha.platform.util.PlatformAccountUtil;
import com.chehaha.system.util.EventUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chehaha.api.payment.IPaymentAdapter;
import com.chehaha.api.payment.wechat.api.WeChatPayApi;
import com.chehaha.common.exception.BizException;
import com.chehaha.common.exception.FrameworkException;
import com.chehaha.common.util.json.impl.FastjsonUtil;
import com.chehaha.common.util.string.ValidatorUtil;
import com.chehaha.order.entity.OrderInfo;

/**
 * @author James.Wong
 * @version V1.0
 * @Title: WechatPayService.java
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2018年2月13日
 */
@Service("wechatpay")
public class WechatPayService implements IPaymentAdapter {

    @Autowired
    private WeChatPayConfig config;
    @Autowired
    private WeChatPayApi api;

    @Override
    public Serializable payOrder(IPayParam payParam) throws BizException, FrameworkException {
        // TODO Auto-generated method stub
        if (payParam.getPayCallBackParam() == null || payParam.getPayCallBackParam().getPayModel() == null) {
            throw new FrameworkException(F7.PLEASE_SELECT_PAY_MODEL, "wechat");
        }
        return api.payUnifiedorder(payParam);//order, param.getIp(), config.getTradeType(payParam.getPayApiType())
    }

    @Override
    public void payCallback(Serializable data, HttpServletRequest request) throws FrameworkException {
        // TODO Auto-generated method stub
        try {
            Map<String, String> xmlmap = WeChatPayApi.parseXml(data.toString());
            if (xmlmap != null) {
                WeChatCallBackResponse weChatCallBackResponse = FastjsonUtil.toObject(FastjsonUtil.parse(xmlmap), WeChatCallBackResponse.class);
                //成功
                if (weChatCallBackResponse.isSuccess()) {
                    //验证sgin
                    if (api.isSignatureValid(xmlmap)) {
                        IPayCallBackParam payCallBackParam = FastjsonUtil.toObject(weChatCallBackResponse.getAttach(), IPayCallBackParam.class);
                        switch (payCallBackParam.getPayModel()){ //根据调用模块选择回调触发
                            case Order:
                                EventUtil.orderPayCallBackEvent(weChatCallBackResponse, PayPipe.Wechat,payCallBackParam);
                                break;
                            case ShopAccount:
                                EventUtil.shopAccountPayCallBackEvent(weChatCallBackResponse, PayPipe.Wechat,payCallBackParam);
                                break;
                        }
                    }
                    //修改金额 微信已分为单位
                    PlatformAccountUtil.changeAmount(Float.parseFloat(weChatCallBackResponse.getTotal_fee())/100F,true, PayPipe.Alipay);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameworkException(F7.WECHAT_CALLBACK_SIGN_ERROR, data.toString(), e.getMessage());
        }
    }

    @Override
    public WeChatTradeQueryResponse payQuery(String tradeNo) {
        //微信有两个APPID  JSAPI 和 APP支付, 未查询之前,无法知晓是哪种方式支付,因此可能需要查询两次(默认查询APP)
        try {
            WeChatTradeQuery appTradeQuery = WeChatTradeQuery.instance(tradeNo, config,config.getAppid());
            Map<String, String> appMap = WeChatPayApi.httpsRequestToXML(config.getQueryApi(), "POST", appTradeQuery.toXml());
            WeChatTradeQueryResponse appQuery = FastjsonUtil.toObject(FastjsonUtil.parse(appMap), WeChatTradeQueryResponse.class);
            if(appQuery.isExist() && appQuery.isPaySuccess()){  // 如果存在(貌似可以省略) 并且已经支付成功
                return  appQuery;
            }else {  //返回JSAPI支付数据
                WeChatTradeQuery jsTradeQuery = WeChatTradeQuery.instance(tradeNo, config,config.getJsAppid());
                Map<String, String> js = WeChatPayApi.httpsRequestToXML(config.getQueryApi(), "POST", jsTradeQuery.toXml());
                return FastjsonUtil.toObject(FastjsonUtil.parse(js), WeChatTradeQueryResponse.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameworkException(F7.WECHAT_QUERY_FAIL, tradeNo+"   "+e.getMessage());
        }
    }

    @Override
    public WeChatCloseOrderResponse closePay(String tradeNo) {
        try {
            //关闭app
            WeChatCloseOrder appCloseOrder = WeChatCloseOrder.instance(tradeNo, config);
            Map<String, String> app = WeChatPayApi.httpsRequestToXML(config.getCloseApi(), "POST", appCloseOrder.toXml());
            WeChatCloseOrderResponse appClose = FastjsonUtil.toObject(FastjsonUtil.parse(app), WeChatCloseOrderResponse.class);
            if(appClose.isSuccess()){ //如果app关闭成功
                return  appClose;
            }else {
                WeChatCloseOrder jsCloseOrder = WeChatCloseOrder.instance(tradeNo, config);
                Map<String, String> js = WeChatPayApi.httpsRequestToXML(config.getCloseApi(), "POST", jsCloseOrder.toXml());
                return FastjsonUtil.toObject(FastjsonUtil.parse(js), WeChatCloseOrderResponse.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameworkException(F7.WECHAT_CLOSE_ORDER_FAIL, tradeNo);
        }
    }

    @Override
    public WeChatTransfersResponse transfer(ITransferParam transferParam) throws FrameworkException {
        Map<String, String> map = api.wehcatTransfers(transferParam);
        try {
            String  s = FastjsonUtil.beanToJson(map);
            WeChatTransfersResponse weChatTransfersResponse =  FastjsonUtil.toObject(s, WeChatTransfersResponse.class);
            if(weChatTransfersResponse.isSuccess()){
                //修改金额 微信已分为单位
                PlatformAccountUtil.changeAmount(Float.parseFloat(transferParam.getAmount())/100F,false, PayPipe.Alipay);
                return  weChatTransfersResponse;
            }
            throw  new FrameworkException(F7.WECHAT_TRANSFER_ERROR,transferParam,weChatTransfersResponse);
        } catch (JsonProcessingException e) {
            throw  new FrameworkException(F7.WECHAT_TRANSFER_ERROR,transferParam,map);
        }

    }

    @Override
    public Serializable refund(OrderInfo order, Serializable response, String reason) throws BizException, FrameworkException {
        // TODO Auto-generated method stub
        WeChatCallBackResponse callbackResponse = (WeChatCallBackResponse) response;

        Map<String, String> map = api.wechatPayTradeRefund(callbackResponse, reason);

        String s = null;
        try {
            s = FastjsonUtil.beanToJson(map);
        } catch (JsonProcessingException e) {
            throw new FrameworkException(F7.WECHAT_RETURN_INFO_READ_FAIL, map);
        }
        WeChatRefundResponse result = FastjsonUtil.toObject(s, WeChatRefundResponse.class);
        try {
            //先验签
            if (api.isSignatureValid(map)) {
                if (!result.isSuccess()) { //如果成功
                    throw new FrameworkException(F7.WECHAT_REFUND_FAIL, result);
                }
                return result;
            } else {
                throw new FrameworkException(F7.WECHAT_CALLBACK_SIGN_ERROR, result, "");
            }
        } catch (Exception ex) {
            throw new FrameworkException(F7.WECHAT_CALLBACK_SIGN_ERROR, result, ex.getMessage());
        }


    }


//	private PaymentInfo getPaymentInfo(WeChatRefundResponse weChatRefundResponse, OrderInfo orderInfo,WeChatCallBackResponse weChatCallBackResponse) {
//        PaymentInfo paymentInfo = PaymentInfo.instance(orderInfo.getId(), com.chehaha.order.Const.PLATFORM_UID,orderInfo.getUid(), PayPipe.Wechat, CleanType.CustRefund);
//        paymentInfo.setCertNo(weChatRefundResponse.getTransaction_id());
//        paymentInfo.setData(FastjsonUtil.parse(weChatRefundResponse));
//        paymentInfo.setStatus(PaymentStatus.Success);
//        paymentInfo.setAmount(weChatRefundRespo
// nse.getTotal_fee()/100D);  //微信以分为单位
//        paymentInfo.setPayAccount(weChatCallBackResponse.getAppid());//付费账号
//        paymentInfo.setRivalAccount(weChatCallBackResponse.getOpenid());//收费账号
//        //    String rivalAccount;//收款账号(商家的)
//        return paymentInfo;
//    }

}
