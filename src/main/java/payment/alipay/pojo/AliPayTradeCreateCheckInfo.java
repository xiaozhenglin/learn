package com.chehaha.api.payment.alipay.pojo;

import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;

/**
 * 支付宝订单创建时返回的信息
 *<P>用于之后支付宝回调的校验</P>
 * Created by DK on 2017/12/22.
 */
public class AliPayTradeCreateCheckInfo {
    /**
     * 商户网站唯一订单号
     */
    private String outTradeNo;

    /**
     * 收款支付宝账号对应的支付宝唯一用户号。
     以2088开头的纯16位数字
     */
    private String sellerId;

    /**
     * 该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。
     */
    private String totalAmount;

    /**
     * 该交易在支付宝系统中的交易流水号。最长64位。
     */
    private String tradeNo;


    public static AliPayTradeCreateCheckInfo getAliPayTradeCreateCheckInfo(AlipayTradeWapPayResponse alipayTradeWapPayResponse){
        return new AliPayTradeCreateCheckInfo(alipayTradeWapPayResponse.getOutTradeNo(),alipayTradeWapPayResponse.getSellerId(),alipayTradeWapPayResponse.getTotalAmount(),alipayTradeWapPayResponse.getTradeNo());
    }

    public static AliPayTradeCreateCheckInfo getAliPayTradeCreateCheckInfo(AlipayTradeAppPayResponse alipayTradeAppPayResponse){
        return new AliPayTradeCreateCheckInfo(alipayTradeAppPayResponse.getOutTradeNo(),alipayTradeAppPayResponse.getSellerId(),alipayTradeAppPayResponse.getTotalAmount(),alipayTradeAppPayResponse.getTradeNo());
    }



    public AliPayTradeCreateCheckInfo(String outTradeNo, String sellerId, String totalAmount, String tradeNo) {
        this.outTradeNo = outTradeNo;
        this.sellerId = sellerId;
        this.totalAmount = totalAmount;
        this.tradeNo = tradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
}
