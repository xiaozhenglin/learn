package com.chehaha.api.wechat.xiao.menu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chehaha.common.exception.BizException;
import com.chehaha.api.wechat.xiao.menu.entity.WechatMenuItem;
import com.chehaha.api.wechat.xiao.menu.pojo.MenuData;


public interface IWechatMenuService {
	
	boolean addItem(MenuData item) throws BizException;
	
	boolean modifyItem(MenuData item) throws BizException;
	
	Page<WechatMenuItem> getItems(Pageable page) throws BizException;
	
	boolean removeItem(int itemId) throws BizException;
	
	boolean synchroData() throws BizException;
}
