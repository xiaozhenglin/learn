package com.chehaha.api.wechat.xiao.menu.pojo;


import java.io.Serializable;

import com.chehaha.common.util.string.StringUtil;
import com.chehaha.api.wechat.xiao.menu.entity.WechatMenuItem;


public class MenuData implements Serializable {

	private static final long serialVersionUID = 762416658672296420L;

	private int id;
	private String name;
	private int type;
	private String value;
	private int pid;

	public MenuData() {}
	
	public WechatMenuItem toEntity() {
		WechatMenuItem item = new WechatMenuItem();
		item.setId(0);
		return toEntity(item);
	}
	
	public WechatMenuItem toEntity(WechatMenuItem item) {
		item.setName(name);
		item.setType(getType());
		if(StringUtil.isNotEmpty(value)) { 
			item.setLinkValue(value);
		}
		item.setGroup(pid);
		return item;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WechatMenuType getType() {
		return WechatMenuType.values()[type];
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

}
