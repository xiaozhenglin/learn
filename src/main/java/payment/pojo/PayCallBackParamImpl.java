package com.chehaha.api.payment.pojo;

import com.chehaha.common.util.json.impl.FastjsonUtil;
import com.chehaha.shop.pojo.ShopAccountPayCallBack;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author DK
 * @date 2018/3/13 18:25
 */
public class PayCallBackParamImpl implements IPayCallBackParam {
    private PayModel payModel;


    private Object callBackInfo;  //反序列化会变成 link map


//    public Object getRealData(){
//        switch (payModel){
//            case Order:
//                return null;
//            case ShopAccount:
//                return FastjsonUtil.toObject( FastjsonUtil.parse(callBackInfo),ShopAccountPayCallBack.class);
//        }
//        return  null;
//    }

    @Override
    public PayModel getPayModel() {
        return payModel;
    }

    public void setPayModel(PayModel payModel) {
        this.payModel = payModel;
    }

    @Override
    public Object getCallBackInfo() {
        return callBackInfo;
    }

    public void setCallBackInfo(Object callBackInfo) {
        this.callBackInfo = callBackInfo;
    }
}
