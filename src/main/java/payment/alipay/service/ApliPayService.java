package com.chehaha.api.payment.alipay.service;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.*;
import com.chehaha.api.payment.pojo.IPayCallBackParam;
import com.chehaha.api.payment.pojo.IPayParam;
import com.chehaha.api.payment.pojo.ITransferParam;
import com.chehaha.common.exception.code.F7;
import com.chehaha.common.util.json.impl.FastjsonUtil;
import com.chehaha.fee.pojo.PayPipe;
import com.chehaha.platform.util.PlatformAccountUtil;
import com.chehaha.system.util.EventUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.chehaha.api.payment.IPaymentAdapter;
import com.chehaha.api.payment.pojo.PayApiType;
import com.chehaha.api.payment.alipay.api.AliPayApi;
import com.chehaha.api.payment.alipay.pojo.AliPayCallBackResponse;
import com.chehaha.common.exception.BizException;
import com.chehaha.common.exception.FrameworkException;
import com.chehaha.order.entity.OrderInfo;
import com.chehaha.order.service.IOrderService;

/**
 * @Title: ApliPayService.java
 * @Description: TODO(用一句话描述该文件做什么)
 * @author James.Wong
 * @date 2018年2月13日
 * @version V1.0
 */
@Service("alipay")
public class ApliPayService implements IPaymentAdapter {

	@Autowired
	private AliPayApi api;

	@Autowired
	private IOrderService orderService;

	@Override
	public Serializable payOrder(IPayParam  payParam)
			throws BizException, FrameworkException {
		// TODO Auto-generated method stub
		if(payParam.getPayCallBackParam() == null || payParam.getPayCallBackParam().getPayModel() == null){
			throw  new FrameworkException(F7.PLEASE_SELECT_PAY_MODEL,"ali");
		}
		try {
			switch (payParam.getPayApiType()) {
			case APP:
                AlipayTradeAppPayResponse alipayTradeAppPayResponse = api.aliPayTradeCreateAppPay(payParam);
                if(alipayTradeAppPayResponse.isSuccess()){
                    return  alipayTradeAppPayResponse;
                }
                throw new FrameworkException(F7.ALIPAY_PAY_FAIL,alipayTradeAppPayResponse,payParam.getCode());
                case H5:
			case JSAPI:
                AlipayTradeWapPayResponse alipayTradeWapPayResponse = api.aliPayTradeCreateWebPay(payParam);
                if(alipayTradeWapPayResponse.isSuccess()){
                    return  alipayTradeWapPayResponse;
                }
                throw new FrameworkException(F7.ALIPAY_PAY_FAIL,alipayTradeWapPayResponse,payParam.getCode());
			default:
				return null;
			}

			// if(!checkAliPayIsSuccess(alipayTradeWapPayResponse)){
			// throw new FrameworkException("创建H5支付失败");
			// }
			// RedisUtil.save(Section.AliPayInfo,alipayTradeWapPayResponse.getTradeNo(),AliPayTradeCreateCheckInfo.getAliPayTradeCreateCheckInfo(alipayTradeWapPayResponse),
			// ExpiredStrategy.THIRTY_MINUTES);

		} catch (AlipayApiException e) {
			throw new FrameworkException(F7.ALIPAY_PAY_FAIL,e.getErrCode(),e.getErrMsg(),payParam.getCode());
		}
	}


	@Override
	public AlipayTradeQueryResponse payQuery(String tradeNo) {
        try {
            AlipayTradeQueryResponse alipayTradeQueryResponse = api.alipayTradeQuery(tradeNo);
            if(alipayTradeQueryResponse.isSuccess()){
                return alipayTradeQueryResponse;
            }
            throw  new FrameworkException(F7.ALIPAY_QUERY_FAIL,alipayTradeQueryResponse,tradeNo);
        } catch (AlipayApiException e) {
            throw new FrameworkException(F7.ALIPAY_QUERY_FAIL,e.getErrCode(),e.getErrMsg(),tradeNo);
        }
    }

	@Override
	public AlipayTradeCloseResponse closePay(String tradeNo) {
		//阿里由于其特殊性, 无法关闭订单.只能在收款回调时, 进行退款
		return  new AlipayTradeCloseResponse();
//		try {
//			return api.alipayTradeClose(tradeNo);
//		} catch (AlipayApiException e) {
//			throw new FrameworkException(F7.ALIPAY_CLOSE_ORDER_FAIL,e.getErrCode(),e.getErrMsg(),tradeNo);
//		}
	}

    @Override
    public AlipayFundTransToaccountTransferResponse transfer(ITransferParam transferParam) {
        try {
            AlipayFundTransToaccountTransferResponse transfer = api.transfer(transferParam);
            if(transfer.isSuccess()){
                return  transfer;
            }
            throw  new FrameworkException(F7.ALIPAY_TRANSFER_ERROR,transfer,transferParam.getCode());
        } catch (AlipayApiException e) {
            throw  new FrameworkException(F7.ALIPAY_TRANSFER_ERROR,e.getErrCode(),e.getErrMsg(),transferParam.getCode());
        }
    }


    @Override
	public void payCallback(Serializable  data, HttpServletRequest request) throws FrameworkException {
		// TODO Auto-generated method stub
		AliPayCallBackResponse resp = (AliPayCallBackResponse) data;
		try {
			boolean checkAliPayCallBackInfo = checkAliPayCallBackInfo(resp, request);
			if (!checkAliPayCallBackInfo) {
				throw new FrameworkException(F7.ORDER_INFO_ERROR);
				// 进行退款
			}
			IPayCallBackParam payCallBackParam = FastjsonUtil.toObject(resp.getPassback_params(), IPayCallBackParam.class);
			switch (payCallBackParam.getPayModel()){
                case Order:
                    EventUtil.orderPayCallBackEvent(resp, PayPipe.Alipay,payCallBackParam);
                    break;
                case ShopAccount:
                    EventUtil.shopAccountPayCallBackEvent(resp, PayPipe.Alipay,payCallBackParam);
                    break;
            }
			//修改金额
			PlatformAccountUtil.changeAmount(Float.parseFloat(resp.getReceipt_amount()+""),true, PayPipe.Alipay);
//			orderService.aliPayOrderOnlineConfirm(resp);
		} catch (AlipayApiException e) {
			throw new FrameworkException(F7.NOT_ALIPAY_CALLBACK_INFO,e.getErrCode(),e.getErrMsg(),data);
		} catch (BizException e) {
			// 退款 ???
			throw e;
		}
	}


//	public  static  void main(String[] a){
//	    String s = "{\"payModel\":\"ShopAccount\",\"callBackInfo\":{\"sid\":4,\"mid\":8}}";
//        IPayCallBackParam payCallBackParam = FastjsonUtil.toObject(s, IPayCallBackParam.class);
//        System.out.println(payCallBackParam);
//    }


	/**
	 * 检查回调信息
	 *
	 * @param aliPayCallBackResponse
	 * @param httpServletRequest
	 * @return
	 * @throws AlipayApiException
	 */
	private boolean checkAliPayCallBackInfo(AliPayCallBackResponse aliPayCallBackResponse,
			HttpServletRequest httpServletRequest) throws AlipayApiException {
		boolean checkAliPay = api.checkAliPay(httpServletRequest);
		if (!checkAliPay) {
			throw new AlipayApiException("不是支付宝的回调信息");
		}
		// AliPayTradeCreateCheckInfo aliPayTradeCreateCheckInfo =
		// RedisUtil.get(Section.AliPayInfo, aliPayCallBackResponse.getTrade_no());
		// if
		// (!StringUtil.equals(aliPayTradeCreateCheckInfo.getOutTradeNo(),aliPayCallBackResponse.getOut_trade_no())){
		// return false;
		// }
		// if
		// (!StringUtil.equals(aliPayTradeCreateCheckInfo.getSellerId(),aliPayCallBackResponse.getSeller_id())){
		// return false;
		// }
		// if
		// (!StringUtil.equals(aliPayTradeCreateCheckInfo.getTradeNo(),aliPayCallBackResponse.getTrade_no())){
		// return false;
		// }
		// if
		// (!StringUtil.equals(aliPayTradeCreateCheckInfo.getTotalAmount(),aliPayCallBackResponse.getTotal_amount())){
		// return false;
		// }
		//
		// RedisUtil.deleteCacheByKey(Section.AliPayInfo,
		// aliPayCallBackResponse.getTrade_no());
		return true;
	}

	@Override
	public AlipayTradeRefundResponse refund(OrderInfo order,Serializable response, String reason) throws BizException, FrameworkException {
		// TODO Auto-generated method stub
		AliPayCallBackResponse callbackResponse = (AliPayCallBackResponse) response;
		AlipayTradeRefundResponse result = null;

		try {
			result = api.alipayTradeRefund(callbackResponse, reason);
		} catch (AlipayApiException e) {
			e.printStackTrace();
			throw new FrameworkException(F7.ALIPAY_REFUND_FAIL,e.getErrCode(), e.getErrMsg());
		}
		if (result == null || !api.checkAliPayIsSuccess(result)) {
			throw new FrameworkException(F7.ALIPAY_REFUND_FAIL,result);
		}
		return result;// getPaymentInfo(result,order,callbackResponse);
	}


//	private PaymentInfo getPaymentInfo(AlipayTradeRefundResponse alipayTradeRefundResponse, OrderInfo orderInfo, AliPayCallBackResponse aliPayCallBackResponse) {
//        PaymentInfo paymentInfo = PaymentInfo.instance(orderInfo.getId(),com.chehaha.order.Const.PLATFORM_UID , orderInfo.getUid(), PayPipe.Alipay, CleanType.CustRefund);
//        paymentInfo.setCertNo(alipayTradeRefundResponse.getTradeNo());
//        paymentInfo.setData(FastjsonUtil.parse(alipayTradeRefundResponse));
//        paymentInfo.setStatus(PaymentStatus.Success);
//        paymentInfo.setAmount(Double.parseDouble(alipayTradeRefundResponse.getRefundFee()));
//        paymentInfo.setPayAccount(aliPayCallBackResponse.getSeller_id());
//        paymentInfo.setRivalAccount(aliPayCallBackResponse.getBuyer_id());
//        //    String rivalAccount;//收款账号(商家的)
//        return paymentInfo;
//    }

}
