package com.chehaha.api.payment.alipay.service.impl;

import com.alipay.api.AlipayApiException;
import com.chehaha.api.payment.alipay.api.AliPayApi;
import com.chehaha.api.payment.alipay.pojo.AliPayCallBackResponse;
import com.chehaha.api.payment.alipay.pojo.AliPayTradeCreateCheckInfo;
import com.chehaha.api.payment.alipay.service.IAliPayService;
import com.chehaha.common.exception.BizException;
import com.chehaha.common.exception.FrameworkException;
import com.chehaha.common.exception.code.F7;
import com.chehaha.common.type.Section;
import com.chehaha.common.util.cache.RedisUtil;
import com.chehaha.common.util.string.StringUtil;
import com.chehaha.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DK on 2017/12/22.
 */
@Service
public class AliPayServiceImpl implements IAliPayService {
    @Autowired
    private IOrderService orderServiceImpl;
    @Autowired
    private AliPayApi api;

//    @Override
//    public void aliPayCallBack(AliPayCallBackResponse aliPayCallBackResponse, HttpServletRequest httpServletRequest) throws FrameworkException {
//        try {
//            checkAliPayCallBackInfo(aliPayCallBackResponse, httpServletRequest);
//            orderServiceImpl.aliPayOrderOnlineConfirm(aliPayCallBackResponse);
//        } catch (AlipayApiException e) {
//            throw  new FrameworkException(F7.NOT_ALIPAY_CALLBACK_INFO,e.getErrCode(),e.getErrMsg());
//        }catch (BizException e){
//            //退款 ???
//            throw e;
//        }
//    }

    /**
     * 检查回调信息
     * @param aliPayCallBackResponse
     * @param httpServletRequest
     * @return
     * @throws AlipayApiException
     */
//    private void checkAliPayCallBackInfo(AliPayCallBackResponse aliPayCallBackResponse, HttpServletRequest httpServletRequest) throws AlipayApiException {
//            boolean checkAliPay = api.checkAliPay(httpServletRequest);
//            if(!checkAliPay){
//                throw new  AlipayApiException(F7.NOT_ALIPAY_CALLBACK_INFO);
//            }
////        AliPayTradeCreateCheckInfo aliPayTradeCreateCheckInfo = RedisUtil.get(Section.AliPayInfo, aliPayCallBackResponse.getTrade_no());
////        if (!StringUtil.equals(aliPayTradeCreateCheckInfo.getOutTradeNo(),aliPayCallBackResponse.getOut_trade_no())){
////            return  false;
////        }
////        if (!StringUtil.equals(aliPayTradeCreateCheckInfo.getSellerId(),aliPayCallBackResponse.getSeller_id())){
////            return  false;
////        }
////        if (!StringUtil.equals(aliPayTradeCreateCheckInfo.getTradeNo(),aliPayCallBackResponse.getTrade_no())){
////            return  false;
////        }
////        if (!StringUtil.equals(aliPayTradeCreateCheckInfo.getTotalAmount(),aliPayCallBackResponse.getTotal_amount())){
////            return  false;
////        }
////
////        RedisUtil.deleteCacheByKey(Section.AliPayInfo, aliPayCallBackResponse.getTrade_no());
//    }
}
