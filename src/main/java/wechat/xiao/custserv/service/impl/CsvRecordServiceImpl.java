package com.chehaha.api.wechat.xiao.custserv.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.chehaha.common.data.pojo.Criterion;
import com.chehaha.common.data.service.ICrudService;
import com.chehaha.common.exception.FrameworkException;
import com.chehaha.common.util.string.StringUtil;
import com.chehaha.api.wechat.xiao.api.WechatConfig;
import com.chehaha.api.wechat.xiao.api.pojo.user.WechatUser;
import com.chehaha.api.wechat.xiao.api.service.CustomerServiceApi;
import com.chehaha.api.wechat.xiao.api.service.WechatUserApi;
import com.chehaha.api.wechat.xiao.custserv.entity.CsvRecord;
import com.chehaha.api.wechat.xiao.custserv.pojo.CsvRecordResult;
import com.chehaha.api.wechat.xiao.custserv.pojo.RequireWeChatRecords;
import com.chehaha.api.wechat.xiao.custserv.pojo.chartadmin.CsvChartList;
import com.chehaha.api.wechat.xiao.custserv.pojo.chartadmin.CsvChartListQuery;
import com.chehaha.api.wechat.xiao.custserv.pojo.chartadmin.SendType;
import com.chehaha.api.wechat.xiao.custserv.repository.IChatRecordsRepository;
import com.chehaha.api.wechat.xiao.custserv.service.ICsvRecordService;
import com.chehaha.api.wechat.xiao.util.TimeUtil;


@Service
public class CsvRecordServiceImpl  implements ICsvRecordService {
	
	@Autowired
	private IChatRecordsRepository chatRecordsRepository;
	
	@Autowired
	private ICrudService crudService;
	
	
	//公众号申请聊天记录
	@Override
	public void collectChartRecords(Date day) throws FrameworkException {
		long endtime = day.getTime() / 1000;
		long starttime = endtime - 43000; 
		// pojo类
		RequireWeChatRecords rwcr = RequireWeChatRecords.instance(starttime, endtime, "1", "10000");
		CsvRecordResult crr = CustomerServiceApi.getChartRecords(rwcr); 
		List<CsvRecord> recordlist = crr.getRecordlist();
		if(recordlist!=null&&recordlist.size()>0) {
			for(CsvRecord s:recordlist) {
				//设置主键
				s.setRecId(TimeUtil.DateToInt(new Date()));
				s.setCustName(getUserNickName(s.getOpenid())); 
				CsvRecord cs = chatRecordsRepository.findByTimeAndText(s.getTime(),s.getText());
				if(cs==null) {
					//保存入库
					chatRecordsRepository.save(s);
				}
			}
		}
	}
	
	private String getUserNickName(String openId) {
		WechatUser wechatUserInfo = WechatUserApi.getWechatUserInfo(openId);
		if(wechatUserInfo!=null) {
			return wechatUserInfo.getNickName();
		}
		return null;
	}

	//1：客服记录列表 管理
	@Override
	public Page<CsvChartList> getCsvCharList(CsvChartListQuery query, Pageable page) {
		List<Criterion> criterions = new ArrayList<>();
		String keyWord =""; String account = "";
		if(query.hasAccount()) {
			account =  query.getAccount()+"@"+WechatConfig.getCustomService().getSuffix();
			criterions.add(Criterion.instance("worker",account));
		}
		if(query.hasKeyword()) {
			keyWord = query.getKeyword();
			criterions.add(Criterion.instance("text|like",keyWord));
		}
		//查出对应人的聊天记录
		Page<CsvRecord> umis = crudService.findByPage(CsvRecord.class, criterions, page);
		List<CsvChartList> list = new ArrayList<>();
		for(CsvRecord cr : umis ) {
			if(cr != null) {
				CsvChartList ccl = getCsvChartList(cr);
				list.add(ccl);
			}
		}
		return new PageImpl<>(list);
	}
	
	private CsvChartList getCsvChartList(CsvRecord cr) {
		String worker = cr.getWorker();
		String account  = worker.substring(0,worker.indexOf("@"));
		
		CsvChartList ccl = new CsvChartList();
		ccl.setId(cr.getRecId());
		ccl.setAccount(account); 
		
//		操作码，2002（客服发送信息），2003（客服接收消息）
		if(cr.getOpercode() == 2002 ) {
			ccl.setType(SendType.Send);
		}else if(cr.getOpercode() == 2003) {
			ccl.setType(SendType.Receive);
		}
		String time = cr.getTime()+"000";
		Date date = new Date(Long.parseLong(time));
		ccl.setChatTime(TimeUtil.dateToString(date));
		
		ccl.setText(cr.getText());
		
		if(cr.getCustName()!=null) {
			ccl.setCustName(cr.getCustName());
		}else {
			ccl.setCustName(getUserNickName(cr.getOpenid())); 
		}
		
		return ccl;
	}

	//2：客服记录删除接口
	@Override
	public String removeChart(String id) {
		List<String> recordIds  = null;
		if(id.contains(",")) {
			recordIds = StringUtil.stringToList(id);
		}else {
			recordIds = new ArrayList<>();
			recordIds.add(id);
		}
		for (String str : recordIds) {
			CsvRecord csvRecord = crudService.get(Integer.parseInt(str), CsvRecord.class);
			if(csvRecord!=null) {
				crudService.delete(csvRecord, CsvRecord.class);
//				final CsvAdminOperation operation = CsvAdminOperation.RemoveRecord;
//				saveUserTrailEvent(operation,csvRecord.getText()+":"+csvRecord.getCustName());
			}
		}
		
		return "remove success";
	}
	
//	private void saveUserTrailEvent(Operation operation,String content) {
//		EventUtil.saveUserTrailEvent(this, BizCode.operation(BizType.Offical,operation), content);
//	}
	

//	@Override
//	public Page<CsvChartList> getCsvCharList(int id, Pageable page) {
//		//获取客服
//		CsvSeat cr = crudService.get(id, CsvSeat.class); 
//		if(cr == null) {
//			return null;
//		}
//		String account  = cr.getKfAccount()+"@"+WechatConfig.getCustomService().getSuffix();
//		//获取聊天记录
//		List<Criterion> criterions = Arrays.asList(
//				Criterion.instance("worker", account));
//		Page<CsvRecord> crs = crudService.findByPage(CsvRecord.class, criterions, page);
//		List<CsvChartList> list = new ArrayList<>();
//		for(CsvRecord crr : crs) {
//			CsvChartList csvChart = getCsvChartList(crr);
//			if(csvChart!=null) {
//				list.add(csvChart);
//			}
//		}
//		return new PageImpl<>(list,page,crs.getTotalElements());
//	}
}
