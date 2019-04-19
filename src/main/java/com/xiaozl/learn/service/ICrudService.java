package com.xiaozl.learn.service;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.xiaozl.learn.pojo.ParamMatcher;

public interface ICrudService<T> {
	List<T> getAll(Class<T> clazz,boolean isPramDataSource);
	
	Page<T> getAllByPage(Class<T> clazz,Pageable page,boolean isPramDataSource);
	
	<T> T get(Serializable id,Class<T> clazz,boolean isPramDataSource);
	
	List<T> findByMoreFiled(Class<T> clazz,Map<String,ParamMatcher> map,boolean isPramDataSource);   
	
	boolean update(Class<T> entity,boolean isPramDataSource);

	boolean delete(Class<T> entity,boolean isPramDataSource);

	boolean save(Class entity,boolean isPramDataSource);

}
