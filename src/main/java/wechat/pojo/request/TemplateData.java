package com.chehaha.api.wechat.pojo.request;

import java.io.Serializable;

/**
 * @author DK
 * @date 2018/7/25 10:42
 */
public class TemplateData implements Serializable{
    private String value;
    private String color;

    public TemplateData(String value,String color){
        this.value = value;
        this.color = color;
    }


    public TemplateData() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
