package com.chehaha.api.wechat.xiao.custserv.service.impl;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.chehaha.api.wechat.xiao.OfficialConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chehaha.common.data.pojo.Criterion;
import com.chehaha.common.data.service.ICrudService;
import com.chehaha.api.wechat.xiao.api.pojo.custserv.CustomerServiceForm;
import com.chehaha.api.wechat.xiao.api.service.CustomerServiceApi;
import com.chehaha.api.wechat.xiao.custserv.entity.CsvSeat;
import com.chehaha.api.wechat.xiao.custserv.exception.CsvException;
import com.chehaha.api.wechat.xiao.custserv.pojo.CsvInfoList;
import com.chehaha.api.wechat.xiao.custserv.pojo.CsvStatus;
import com.chehaha.api.wechat.xiao.custserv.pojo.regist.CsvRemoveResult;
import com.chehaha.api.wechat.xiao.custserv.service.ICsvAdminService;

@Service
public class CsvAdminServiceImpl implements ICsvAdminService{
	@Autowired
	private ICrudService crudService;
	
	//获取客服列表信息
	@Override
	public CsvInfoList acquireCsvInformation() {
		CsvInfoList vil = CustomerServiceApi.acquireCsvInformation(); 
		return vil;
	}
	
	//坐席添加
	@Override
	public boolean seatCreate(String nickname, String account, String wxcode) {
		CustomerServiceForm csf = new CustomerServiceForm();
		csf.setUsername(account); //账户
   		csf.setNickname(nickname);  //昵称
		csf.setWechatAccount(wxcode); //微信号 
		
		try {
			String avatar = URLDecoder.decode(getClass().getResource(OfficialConst.CSV_HEAD_IMAGE).getFile(),"UTF-8");
			csf.setAvatar(new File(avatar)); 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(CustomerServiceApi.addCustomerService(csf)) { 
			CsvSeat cs = new CsvSeat();
			cs.setKfAccount(account);
			cs.setCode(wxcode);
			cs.setNickName(nickname); 
			cs.setRegTime(new Date()); 
			cs.setStatus(CsvStatus.Approved);
			crudService.save(cs, CsvSeat.class);
			
			//轨迹保存
//			final CsvAdminOperation operation = CsvAdminOperation.CreateCsv;
//			saveUserTrailEvent(operation,nickname+":"+account);
			
			return true;
		}else {
			return false;
		} 
	}
	
//	private void saveUserTrailEvent(Operation operation,String content) {
//		EventUtil.saveUserTrailEvent(this, BizCode.operation(BizType.Offical,operation), content);
//	}

	//删除客服接口
	@Override
	public String removeCsv(String account) {
		CsvRemoveResult crr = CustomerServiceApi.deleteCsvAccount(account);
		//判断是否在微信公众号中删除成功或者不存在该账户去删除
		if(crr.getErrcode() == 0 || crr.getErrcode() == 65401){
			List<Criterion> criterions = Arrays.asList(
					Criterion.instance("kfAccount", account)
					);
			List<CsvSeat> find = crudService.find(CsvSeat.class,criterions);
			if(find.size()>0) {
				for(CsvSeat cs : find ) {
					crudService.delete(cs, CsvSeat.class);
					//轨迹保存
//					final CsvAdminOperation operation = CsvAdminOperation.RemoveCsv;
//					saveUserTrailEvent(operation,cs.getNickName()+":"+account);
				}
			}
			return OfficialConst.removeCsvSeatSuccess;
		}else {
			return crr.getMsg();
		}
	}
}
