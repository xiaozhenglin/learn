package com.chehaha.api.wechat.xiao.menu.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chehaha.common.data.service.ICrudService;
import com.chehaha.common.exception.BizException;
import com.chehaha.system.BizService;
import com.chehaha.system.pojo.BizCode;
import com.chehaha.system.service.IUserAuditService;
import com.chehaha.system.util.EventUtil;
import com.chehaha.api.wechat.xiao.api.pojo.menu.WechatMenuButton;
import com.chehaha.api.wechat.xiao.api.pojo.menu.WholeMenuData;
import com.chehaha.api.wechat.xiao.api.service.WechatMenuApi;
import com.chehaha.api.wechat.xiao.menu.entity.WechatMenuItem;
import com.chehaha.api.wechat.xiao.menu.pojo.MenuData;
import com.chehaha.api.wechat.xiao.menu.service.IWechatMenuService;



@Service
public class MenuServiceImpl  implements IWechatMenuService{

	
	@Autowired
	private ICrudService crudService;
	@Autowired
	private IUserAuditService auditService;

	
//	private void saveUserTrailEvent(Operation operation,String content) {
//		EventUtil.saveUserTrailEvent(this, BizCode.operation(BizType.Offical,OfficalModule.Menu, operation), content);
//	}
	
	@Override
	public boolean addItem(MenuData item) throws BizException {
		// TODO Auto-generated method stub
//		final MenuOperation operation = MenuOperation.Add;
		WechatMenuItem menu = item.toEntity();
		auditService.setCreateInfo(menu);
		crudService.save(menu, WechatMenuItem.class);
//		saveUserTrailEvent(operation,item.getName());
		return true;
	}

	@Override
	public boolean modifyItem(MenuData item) throws BizException {
		// TODO Auto-generated method stub
//		final MenuOperation operation = MenuOperation.Modify;
		WechatMenuItem originItem = crudService.get(item.getId(), WechatMenuItem.class);
		WechatMenuItem menu = item.toEntity(originItem);		
		auditService.setModifyInfo(menu);
		crudService.save(menu, WechatMenuItem.class);
//		saveUserTrailEvent(operation,item.getName());
		return true;
	}

	@Override
	public Page<WechatMenuItem> getItems(Pageable page) throws BizException {
		// TODO Auto-generated method stub
		return crudService.findByPage(WechatMenuItem.class, new ArrayList<>(), page);
	}

	@Override
	public boolean removeItem(int itemId) throws BizException {
		// TODO Auto-generated method stub
//		final MenuOperation operation = MenuOperation.Delete;
		WechatMenuItem item = crudService.get(itemId, WechatMenuItem.class);
		if(item != null) {
			crudService.delete(item, WechatMenuItem.class);
//			saveUserTrailEvent(operation,itemId+"");
			return true;
		}
		throw new BizException("没有此菜单",itemId);		
	}

	@Override
	public boolean synchroData() throws BizException {
		// TODO Auto-generated method stub
		List<WechatMenuItem> items = crudService.getAll(WechatMenuItem.class,new Sort("group"));
		
		List<WechatMenuButton> buttons = convertToButton(items);
		WholeMenuData data = new WholeMenuData(buttons);
		boolean result = WechatMenuApi.setMenu(data);
		
		return result;
	}
	
	private List<WechatMenuButton> convertToButton(List<WechatMenuItem> items) {
		Map<Integer,WechatMenuButton> buttons = new HashMap<>();
		boolean isSubItem = false;
		for(WechatMenuItem item : items) {
			isSubItem = item.getGroup()>0 ;
			WechatMenuButton btn = new WechatMenuButton();
			switch(item.getType()) {
				case Button:
					btn.setKey(item.getLinkValue());
					break;
				case View:
					btn.setUrl(item.getLinkValue());
					break;
				case Unknown:
			default:
				break;
			}
			btn.setName(item.getName());
			btn.setButtonType(item.getType().toWechatType());
			if(isSubItem) {
//				btn.setButtonType(item.getType().toWechatType());
				buttons.get(item.getGroup()).addSubButton(btn);
			}else {
				buttons.put(item.getId(),btn);
			}
		}
		
		List<WechatMenuButton> list = new ArrayList<>();
		
		for(int index : buttons.keySet()) {
			list.add(buttons.get(index));
		}
		
		return list;
		
	}

	
	
}
