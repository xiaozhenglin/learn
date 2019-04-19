package com.chehaha.api.payment.pojo;

import java.io.Serializable;

/**
 * 转账参数
 * @author DK
 * @date 2018/3/15 18:40
 */
public interface ITransferParam  extends Serializable{




    String getCode();  //支付号
    String getRivalAccount(); //收款账号
    String getAmount(); //金额 单位为元
    String getRemark(); //转账备注
    ITransferChannelUniqueParam getTransferChannelUniqueParam();


    static  ITransferParam instance( String code,String rivalAccount,String amount,String remark,ITransferChannelUniqueParam transferChannelUniqueParam){
        return new ITransferParam() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getRivalAccount() {
                return rivalAccount;
            }

            @Override
            public String getAmount() {
                return amount;
            }

            @Override
            public String getRemark() {
                return remark;
            }

            @Override
            public ITransferChannelUniqueParam getTransferChannelUniqueParam() {
                return transferChannelUniqueParam;
            }
        };
    }
}
