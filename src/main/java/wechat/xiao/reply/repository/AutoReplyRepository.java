package com.chehaha.api.wechat.xiao.reply.repository;

import javax.transaction.Transactional;

import com.chehaha.common.dao.DaoUtil;
import com.chehaha.common.util.SpringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chehaha.api.wechat.xiao.reply.entity.AutoReply;

@Repository
@Transactional
public interface AutoReplyRepository extends JpaRepository<AutoReply, Integer>{
		AutoReply findByKeyWord(String keyWord);
}
