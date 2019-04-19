package com.chehaha.api.wechat.xiao.reply.pojo;

import java.io.Serializable;

import com.chehaha.common.util.string.StringUtil;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

public class ReplyCodeQuery implements Serializable{
	
	private static final long serialVersionUID = 870160428678597267L;
	@JsonDeserialize(using=StringDeserializer.class) 
	private String id;
	@JsonDeserialize(using=StringDeserializer.class) 
	private String key;
	@JsonDeserialize(using=StringDeserializer.class) 
	private String text;
	
	public boolean hasId() {
		return StringUtil.isNotEmpty(id);
	}
	public boolean hasKey() {
		return StringUtil.isNotEmpty(key); 
	}
	public boolean hasText() {
		return StringUtil.isNotEmpty(text);
	}
	public int getId() {
		return Integer.parseInt(id); 
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
