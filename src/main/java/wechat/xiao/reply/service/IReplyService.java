package com.chehaha.api.wechat.xiao.reply.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chehaha.common.exception.FrameworkException;
import com.chehaha.api.wechat.xiao.reply.entity.AutoReply;
import com.chehaha.api.wechat.xiao.reply.pojo.AutoReplyMode;
import com.chehaha.api.wechat.xiao.reply.pojo.ReplyCodeQuery;
import com.chehaha.api.wechat.xiao.reply.pojo.ReplyCodeQueryResult;

public interface IReplyService {
	/**
	 * @param mode
	 * @param openid
	 * @param content
	 * @return 
	 * @throws FrameworkException
	 * 被动回复可用 xiao 
	 */
	String autoReplyMessage(AutoReplyMode mode,String openid,String content) throws FrameworkException;

	String wechatcodeAdd(String keyword, String text, int id);

	Page<ReplyCodeQueryResult> queryForAutoReply(ReplyCodeQuery query, Pageable pageable);  

	String removeRelyCode(int id);

	String updateContent(ReplyCodeQuery query);     
	 
	
	
}
