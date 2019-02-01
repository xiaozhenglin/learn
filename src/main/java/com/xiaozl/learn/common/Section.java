package com.xiaozl.learn.common;


import java.util.ArrayList;
import java.util.List;

/**
 * 值枚举 Created by DK on 2017/11/14.
 */
public enum Section {

	SIMPLE_EXAMPLE("test","全局配置", false ), 
	Static("static","全局配置",false),
	Resource("resource", "全局配置",false),
	Config("config","全局配置", false), // 全局配置
	Workflow("workflow","流程配置信息", false); // 流程配置信息


	private String code;
	private String name;
	private boolean reloadData;
//	private RedisDataType type;
//	private Class<? extends IReloadDataService> bean;
	
	private int expire;
	
	private Section(String code, String name, boolean reloadData) {
		this.code = code;
		this.name = name;
		this.reloadData = reloadData;
		this.expire = -1;
	}
	

	public String getCode() {
		return  RedisConfiguration.prefix + code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isReloadData() {
		return reloadData;
	}

	public void setReloadData(boolean reloadData) {
		this.reloadData = reloadData;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	
	

	
	
	

}
