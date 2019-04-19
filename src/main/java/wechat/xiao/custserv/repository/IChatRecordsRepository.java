package com.chehaha.api.wechat.xiao.custserv.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chehaha.api.wechat.xiao.custserv.entity.CsvRecord;

@Transactional
@Repository
public interface IChatRecordsRepository extends JpaRepository<CsvRecord, Integer>{

	CsvRecord findByTimeAndText(int chartTime, String message);

	
}
