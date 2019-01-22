package com.chehaha.api.wechat.xiao.reply.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.chehaha.api.wechat.xiao.OfficialConst;
import com.chehaha.api.wechat.xiao.OfficialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chehaha.admin.entity.SystemUserInfo;
import com.chehaha.common.data.pojo.Criterion;
import com.chehaha.common.data.pojo.IdNamePair;
import com.chehaha.common.data.service.ICrudService;
import com.chehaha.common.exception.BizException;
import com.chehaha.common.exception.FrameworkException;
import com.chehaha.common.util.InstanceUtil;
import com.chehaha.api.wechat.xiao.reply.entity.AutoReply;
import com.chehaha.api.wechat.xiao.reply.pojo.AutoReplyMode;
import com.chehaha.api.wechat.xiao.reply.pojo.CodeActive;
import com.chehaha.api.wechat.xiao.reply.pojo.PhotoMessage;
import com.chehaha.api.wechat.xiao.reply.pojo.ReplyCodeQuery;
import com.chehaha.api.wechat.xiao.reply.pojo.ReplyCodeQueryResult;
import com.chehaha.api.wechat.xiao.reply.pojo.TextMessage;
import com.chehaha.api.wechat.xiao.reply.service.IReplyService;

@Service
public  class ReplyServiceImpl  implements IReplyService{
	
	@Autowired
	private ICrudService crudService;

	@Override
	public String autoReplyMessage(AutoReplyMode mode, String openid, String content) throws FrameworkException {
		
		String message = "";
		switch (mode) {
			case TEXT:
				if(content!=null) {
					TextMessage tm = new TextMessage();
					tm.toXml(openid, content);
					message=tm.getMsg();
				}
				break;
			case PHOTO:
				PhotoMessage pm=new PhotoMessage();
				pm.setXML(openid, content);
				message=pm.getPhotoMsg();
				break;
			default:
				break;
		}
		return message;
	}
	
	//添加
	@Override
	public String wechatcodeAdd(String keyword, String text,int id) {
		List<Criterion> criterions = Arrays.asList(
				Criterion.instance("keyWord", keyword));
		AutoReply findOne = crudService.findOne(criterions, AutoReply.class); 
		if(findOne!=null) {
			throw new BizException(OfficialException.WECHAT_CODE_IS_EXIST);
		}else {
			//保存入库
			 AutoReply auto=new AutoReply(keyword, 0,text,CodeActive.active,id, new Date(),id, new Date());
			 AutoReply save = crudService.save(auto, AutoReply.class); 
			 if(save!=null) {
				 
				//轨迹保存
//				 final ReplyAdminOperation operation = ReplyAdminOperation.Add;
//				 saveUserTrailEvent(operation,keyword+":"+text);
				 
				 return OfficialConst.addCodeSuccess;
			 }else {
				 throw new BizException(OfficialException.SAVE_CODE_ERROR);
			 }
		}
	}
	
//	private void saveUserTrailEvent(Operation operation,String content) {
//		EventUtil.saveUserTrailEvent(this, BizCode.operation(BizType.Offical,operation), content);
//	}
	
	//查询
	@Override
	public Page<ReplyCodeQueryResult> queryForAutoReply(ReplyCodeQuery query,Pageable pageable) {
		 Page<AutoReply> autos ;
		 List<Criterion> criterions = new ArrayList<>();
		 boolean haveParam = false;
		 //有Id的时候
		 if(query.hasId()) {
			 haveParam = true;
			 criterions.add(Criterion.instance("id", query.getId()));
		 }
		 if(query.hasKey()) {
			 haveParam = true;
			 criterions.add(Criterion.instance("keyWord", query.getKey()));
		 } 
		 if(query.hasText()) {
			 haveParam = true;
			 criterions.add(Criterion.instance("text|like", query.getText()));
		 }
		//不传Id
		 if(!haveParam) {
			 autos = crudService.getAllByPage(AutoReply.class, pageable); 
		 }else {
			 autos = crudService.findByPage(AutoReply.class, criterions, pageable);
		 }
		 //整合
		List<ReplyCodeQueryResult> list = new ArrayList<>();
		if(autos.getTotalElements()>0) {
			for(AutoReply cr : autos) {
				ReplyCodeQueryResult rcqr = new ReplyCodeQueryResult(cr, getUser(cr.getCreatUser()), getUser(cr.getLastUser()));
				list.add(rcqr);
			}
			return new PageImpl<>(list, pageable, autos.getTotalElements());
		}else {
			return null;
		}
	}
	
	private IdNamePair<Long> getUser(int id){
		SystemUserInfo systemUserInfo = crudService.get((long)id, SystemUserInfo.class);
		IdNamePair<Long> user = InstanceUtil.newIdNamePair((long)id,systemUserInfo.getName());
		return user;
	}
	
	//删除
	@Override
	public String removeRelyCode(int id) {
		 AutoReply autoReply = crudService.get(id, AutoReply.class);
		 if(autoReply == null) {
			 throw new BizException(OfficialException.NOT_FIND_CODE);
		 }
		 
		 //轨迹保存
//		 final ReplyAdminOperation operation = ReplyAdminOperation.Delete;
//		 saveUserTrailEvent(operation,id+":"+autoReply.getText());
		 
		 crudService.delete(autoReply, AutoReply.class); 
		 
		 return OfficialConst.deleteCodeSuccess;		
	}
	
	//修改
	@Override
	public String updateContent(ReplyCodeQuery query) {
		 AutoReply autoReply = null;
		 if(query.hasId()&&query.hasKey()) {
			 autoReply =  crudService.get(query.getId(), AutoReply.class);
			 if(autoReply == null) {
				 throw new BizException(OfficialException.NOT_FIND_CODE);
			 }
			 autoReply.setKeyWord(query.getKey()); 
		 }else {
			 throw new BizException(OfficialException.PARAM_IS_NULL);
		 }
		 if(query.hasText()) {
			 autoReply.setText(query.getText());
		 }
		 //轨迹保存
//		 final ReplyAdminOperation operation = ReplyAdminOperation.Delete;
//		 saveUserTrailEvent(operation,query.getKey()+":"+query.getText());
		 
		 AutoReply save = crudService.save(autoReply,AutoReply.class);
		 return save !=null ? OfficialConst.updateCodeSuccess :OfficialConst.updateError;
	}

}
