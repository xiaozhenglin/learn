package com.chehaha.api.payment.wechat.pojo.gongzhonghao.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author DK
 * @date 2018/5/29 14:18
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class BaseResponse implements Serializable {

    private Integer errcode;
    private String errmsg;

    public boolean isSuccess(){
        return  errcode == null || errcode ==0;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
