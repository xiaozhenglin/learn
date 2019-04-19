package com.chehaha.api.wechat.xiao.menu.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.chehaha.common.data.entity.AuditInfo;
import com.chehaha.api.wechat.xiao.menu.pojo.WechatMenuType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_WCT_MENU")
public class WechatMenuItem extends AuditInfo{

	private static final long serialVersionUID = 6894923132969166058L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MENU_ID")
	private int id;
	@Column(name = "MENU_NAME")
	private String name;
	
	@JsonIgnore
	@Enumerated
	@Column(name = "MENU_TYPE")
	private WechatMenuType type;
	
	@Column(name = "GROUP_ID")
	private int group;

	private String linkValue;

	public int getMenuType(){
		return type.ordinal();
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
		return type;
	}

	public void setType(WechatMenuType type) {
		this.type = type;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public String getLinkValue() {
		return linkValue;
	}

	public void setLinkValue(String linkValue) {
		this.linkValue = linkValue;
	}

}
