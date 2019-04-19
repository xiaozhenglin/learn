package com.chehaha.api.payment.pojo;

import com.chehaha.message.pojo.MessageUserImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author DK
 * @date 2018/3/13 18:24
 */
@JsonDeserialize(as =PayCallBackParamImpl.class)
public interface IPayCallBackParam extends Serializable  {

    static IPayCallBackParam instance(PayModel payModel,Serializable callBackInfo){
        return new IPayCallBackParam() {
            @Override
            public PayModel getPayModel() {
                return payModel;
            }

            @Override
            public Object getCallBackInfo() {
                return callBackInfo;
            }
        };
    }


    PayModel getPayModel();

    Object getCallBackInfo();
}
