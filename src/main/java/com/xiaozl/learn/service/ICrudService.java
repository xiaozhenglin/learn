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
	List<T> getAll(Class<T> clazz);
	
	Page<T> getAllByPage(Class<T> clazz,Pageable page);
	
	<T> T get(Serializable id,Class<T> clazz);
	
	List<T> findByMoreFiled(Class<T> clazz,Map<String,ParamMatcher> map);   
	
	boolean update(Class<T> entity);

	boolean delete(Class<T> entity);

	boolean save(Class entity);

}
