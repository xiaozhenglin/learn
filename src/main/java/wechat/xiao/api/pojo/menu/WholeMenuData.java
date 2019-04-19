package com.chehaha.api.wechat.xiao.api.pojo.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WholeMenuData implements Serializable {

	private static final long serialVersionUID = -848305426451459948L;

	private List<WechatMenuButton> button;

	public WholeMenuData(List<WechatMenuButton> buttons) {
		if(buttons == null) {
			button = new ArrayList<>();
		}else {
			button = buttons;
		}		
	}
	
	public List<WechatMenuButton> getButton() {
		return button;
	}

	
}
