package com.xiaozl.learn.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICrudDao<T> {
	
	List<T> getAll(Class<T> clazz);
	
	Page<T> getAllByPage(Class<T> clazz,Pageable page);
	
	<T> T get(Serializable id,Class<T> clazz);
	
    List<T> findByMoreFiled(Class<T> clazz,LinkedHashMap<String,Object> map);

	boolean update(Class<T> entity);

	boolean delete(Class<T> entity);

	boolean save(Class entity);   
    
    
}
