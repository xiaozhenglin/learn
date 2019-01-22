package com.chehaha.api.payment.alipay.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.chehaha.api.payment.Const;
import com.chehaha.api.payment.alipay.AliPayConfig;
import com.chehaha.api.payment.alipay.pojo.AliPayCallBackResponse;
import com.chehaha.api.payment.pojo.IPayParam;
import com.chehaha.api.payment.pojo.ITransferParam;
import com.chehaha.common.util.json.impl.FastjsonUtil;
import com.chehaha.common.util.string.DecimalUtil;

import javax.servlet.http.HttpServletRequest;

import com.chehaha.fee.pojo.PayPipe;
import com.chehaha.platform.util.PlatformAccountUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by DK on 2017/12/8.
 */
@Service
public class AliPayApi implements InitializingBean{
	
	@Autowired
	private AliPayConfig config;

    private AlipayClient alipayClient;
    private AlipayTradeAppPayRequest alipayTradeAppPayRequest = new AlipayTradeAppPayRequest();
    private AlipayTradeWapPayRequest alipayTradeWapPayRequest = new AlipayTradeWapPayRequest();
    private AlipayTradeRefundRequest  alipayTradeRefundRequest = new AlipayTradeRefundRequest();
    private  AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();
    private AlipayTradeCloseRequest alipayTradeCloseRequest = new AlipayTradeCloseRequest();
    private  AlipayFundTransToaccountTransferRequest transToaccountTransferRequest = new AlipayFundTransToaccountTransferRequest();
//    private  AlipayTradeCreateRequest alipayTradeCreateRequest = new AlipayTradeCreateRequest();
    @Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
    	alipayClient = new DefaultAlipayClient(config.getGatewayApi(), config.getAppid(), config.getPrivateKey(), config.getFormat(), config.getCharset(), config.getPublicKey(), config.getSignType());
	}


    /**
     * 转账
     * @param transferParam
     * @return
     */
	public  AlipayFundTransToaccountTransferResponse transfer(ITransferParam transferParam) throws AlipayApiException {
        AlipayFundTransToaccountTransferModel model =new AlipayFundTransToaccountTransferModel();
        model.setAmount(transferParam.getAmount());
        model.setOutBizNo(transferParam.getCode());
        model.setPayeeType("ALIPAY_USERID"); //只支持 支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
        model.setPayeeAccount(transferParam.getRivalAccount());
        model.setRemark(transferParam.getRemark());
        model.setPayerShowName(Const.COMPANY_NAME);
        return transfer(model);
    }

    /**
     * 转账
     * @param model
     * @return
     */
    public  AlipayFundTransToaccountTransferResponse transfer(AlipayFundTransToaccountTransferModel model) throws AlipayApiException {
        transToaccountTransferRequest.setBizModel(model);
        AlipayFundTransToaccountTransferResponse execute = alipayClient.execute(transToaccountTransferRequest);
        //修改金额
        PlatformAccountUtil.changeAmount(Float.parseFloat(model.getAmount()),false, PayPipe.Alipay);
        return execute;
    }

    /**
     * 创建APP订单
     *
     * @param payParam
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeAppPayResponse aliPayTradeCreateAppPay(IPayParam payParam) throws AlipayApiException {
        AlipayTradeAppPayModel alipayTradeAppPayModel = new AlipayTradeAppPayModel();
        alipayTradeAppPayModel.setSubject(Const.COMPANY_NAME);
        alipayTradeAppPayModel.setOutTradeNo(payParam.getCode());
        alipayTradeAppPayModel.setTotalAmount(DecimalUtil.doubleToString(payParam.getAmount()));
        alipayTradeAppPayModel.setPassbackParams(FastjsonUtil.parse(payParam.getPayCallBackParam()));
        alipayTradeAppPayModel.setTimeoutExpress("30m");  //交易30分钟后关闭
        return aliPayTradeCreateAppPay(alipayTradeAppPayModel);
    }

        /**
     * 创建APP订单
     *
     * @param alipayTradeAppPayModel
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeAppPayResponse aliPayTradeCreateAppPay(AlipayTradeAppPayModel alipayTradeAppPayModel) throws AlipayApiException {
        alipayTradeAppPayRequest.setBizModel(alipayTradeAppPayModel);
        alipayTradeAppPayRequest.setNotifyUrl(config.getNotifyUrl());
        return alipayClient.sdkExecute(alipayTradeAppPayRequest);
    }


    /**
     * 创建H5 订单
     * @param payParam
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeWapPayResponse aliPayTradeCreateWebPay(IPayParam  payParam) throws AlipayApiException {
        AlipayTradeWapPayModel alipayTradeAppPayModel = new AlipayTradeWapPayModel();
        alipayTradeAppPayModel.setSubject(Const.COMPANY_NAME);
        alipayTradeAppPayModel.setOutTradeNo(payParam.getCode());
        alipayTradeAppPayModel.setTotalAmount(DecimalUtil.doubleToString(payParam.getAmount()));
        alipayTradeAppPayModel.setPassbackParams(FastjsonUtil.parse(payParam.getPayCallBackParam()));
        alipayTradeAppPayModel.setTimeoutExpress("30m");  //交易30分钟后关闭
        return aliPayTradeCreateWebPay(alipayTradeAppPayModel);
    }
    /**
     * 创建H5 订单
     * @param alipayTradeWapPayModel
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeWapPayResponse aliPayTradeCreateWebPay(AlipayTradeWapPayModel alipayTradeWapPayModel) throws AlipayApiException {
        alipayTradeWapPayRequest.setBizModel(alipayTradeWapPayModel);
        alipayTradeWapPayRequest.setNotifyUrl(config.getNotifyUrl());
        return alipayClient.sdkExecute(alipayTradeWapPayRequest);
    }


    /**
     * 订单查询
     * @param tradeNo
     * @return
     */
    public AlipayTradeQueryResponse alipayTradeQuery(String tradeNo) throws AlipayApiException {
        AlipayTradeQueryModel alipayTradeQueryModel = new AlipayTradeQueryModel();
        alipayTradeQueryModel.setOutTradeNo(tradeNo);
        return alipayTradeQuery(alipayTradeQueryModel);
    }
    /**
     * 订单查询
     * @param alipayTradeQueryModel
     * @return
     */
    public AlipayTradeQueryResponse alipayTradeQuery(AlipayTradeQueryModel alipayTradeQueryModel) throws AlipayApiException {
        alipayTradeQueryRequest.setBizModel(alipayTradeQueryModel);
        return alipayClient.execute(alipayTradeQueryRequest);
    }

    /**
     * 交易关闭
     * @param tradeNo
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeCloseResponse alipayTradeClose(String tradeNo) throws AlipayApiException {
        AlipayTradeCloseModel alipayTradeCloseModel = new AlipayTradeCloseModel();
        alipayTradeCloseModel.setOutTradeNo(tradeNo);
        return alipayTradeClose(alipayTradeCloseModel);
    }

    /**
     * 交易关闭
     * @param alipayTradeCloseModel
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeCloseResponse alipayTradeClose(AlipayTradeCloseModel alipayTradeCloseModel) throws AlipayApiException {
        alipayTradeCloseRequest.setBizModel(alipayTradeCloseModel);
        return alipayClient.execute(alipayTradeCloseRequest);
    }
    /**
     * 支付宝校验是否是支付宝信息
     */
    public boolean checkAliPay(HttpServletRequest httpServletRequest) throws AlipayApiException {
        return AlipaySignature.rsaCheckV1(getCallBackAllParam(httpServletRequest), config.getPublicKey(), config.getCharset(), config.getSignType());
    }

    //2017121921001004720271173832

    private Map<String, String> getCallBackAllParam(HttpServletRequest httpServletRequest) {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = httpServletRequest.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        return params;
    }


    /**
     * 支付宝退款
     * @param aliPayCallBackResponse
     * @param refundReason   退款原因
     * @return
     */
    public  AlipayTradeRefundResponse alipayTradeRefund(AliPayCallBackResponse aliPayCallBackResponse,String refundReason) throws AlipayApiException {
        AlipayTradeRefundModel alipayTradeRefundModel = new AlipayTradeRefundModel();
        alipayTradeRefundModel.setOutTradeNo(aliPayCallBackResponse.getOut_trade_no());
        alipayTradeRefundModel.setTradeNo(aliPayCallBackResponse.getTrade_no());
        alipayTradeRefundModel.setRefundAmount(aliPayCallBackResponse.getBuyer_pay_amount()+"");
        alipayTradeRefundModel.setRefundReason(refundReason);
        return alipayTradeRefund(alipayTradeRefundModel);
    }

    /**
     * 支付宝退款
     * @param alipayTradeRefundModel
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeRefundResponse alipayTradeRefund(AlipayTradeRefundModel alipayTradeRefundModel) throws AlipayApiException {
        alipayTradeRefundRequest.setBizModel(alipayTradeRefundModel);
        AlipayTradeRefundResponse execute = alipayClient.execute(alipayTradeRefundRequest);
        //修改金额
        PlatformAccountUtil.changeAmount(Float.parseFloat(alipayTradeRefundModel.getRefundAmount()),false, PayPipe.Alipay);
        return  execute;
    }

    /**
     * 检查支付宝是否创建成功
     * <url>https://docs.open.alipay.com/common/105806</url>
     *
     * @param alipayResponse
     * @return
     */
    public boolean checkAliPayIsSuccess(AlipayResponse alipayResponse) {
        return com.chehaha.api.payment.Const.ALIPAY_SUCCESS_CODE.equals(alipayResponse.getCode());
    }

	
}
