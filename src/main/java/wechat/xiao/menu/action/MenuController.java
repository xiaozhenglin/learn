package com.chehaha.api.wechat.xiao.menu.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chehaha.common.action.BaseController;
import com.chehaha.common.action.pojo.RestResult;
import com.chehaha.api.wechat.xiao.menu.entity.WechatMenuItem;
import com.chehaha.api.wechat.xiao.menu.pojo.MenuData;
import com.chehaha.api.wechat.xiao.menu.service.IWechatMenuService;


@RestController
@RequestMapping("/admin/offical/menu")
public class MenuController extends BaseController{
	
	@Autowired
	private IWechatMenuService  menuService;
	
	@PostMapping("/add")
	public ResponseEntity<RestResult<String>> add(MenuData item){
		menuService.addItem(item);
		return success();
	}
	
	@PostMapping("/modify")
	public ResponseEntity<RestResult<String>> modify(MenuData item){
		menuService.modifyItem(item);
		return success();
	}
	
	@PostMapping("/del")
	public ResponseEntity<RestResult<String>> delete(int id){
		menuService.removeItem(id);
		return success();
	}
	
	@PostMapping("/syndata")
	public ResponseEntity<RestResult<String>> synData(){
		menuService.synchroData();
		return success();
	}
	
	@GetMapping("/list")
	public ResponseEntity<RestResult<Page<WechatMenuItem>>> getList(){
		Page<WechatMenuItem> result = menuService.getItems(getPage());
		return success(result);
	}
}
