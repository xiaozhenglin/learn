package com.xiaozl.pay.alipay.pojo;


import java.io.Serializable;

public interface IPayParam extends Serializable{

    PayApiType getPayApiType(); //支付方式

    String  getCode();  // 唯一标示

    double  getAmount(); //金额

    IPayCallBackParam  getPayCallBackParam();  //回调信息

    IPayChannelUniqueParam getPayChannelUniqueParam();  //支付渠道特有参数

    static IPayParam instance(OrderInfo orderInfo,PayApiType payApiType,IPayChannelUniqueParam payChannelUniqueParam){
        return  instance(orderInfo.getCode(),orderInfo.getRealPay(),payApiType,IPayCallBackParam.instance(PayModel.Order,null),payChannelUniqueParam);
    }
    static IPayParam instance(String code,double amount,PayApiType payApiType,IPayCallBackParam payCallBackParam,IPayChannelUniqueParam payChannelUniqueParam){
        return new IPayParam() {
            @Override
            public PayApiType getPayApiType() {
                return payApiType;
            }

            @Override
            public String getCode() {
                return code;
            }

            @Override
            public double getAmount() {
                return amount;
            }

            @Override
            public IPayCallBackParam getPayCallBackParam() {
                return payCallBackParam;
            }

            @Override
            public IPayChannelUniqueParam getPayChannelUniqueParam() {
                return payChannelUniqueParam;
            }
        };
    }


}
