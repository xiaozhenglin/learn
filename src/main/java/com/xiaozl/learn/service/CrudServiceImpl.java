package com.xiaozl.learn.service;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xiaozl.learn.dao.ICrudDao;

@Service
public class CrudServiceImpl<T> implements ICrudService{
	
	@Autowired
	ICrudDao<T> dao;

	@Override
	public List getAll(Class clazz) {
		return dao.getAll(clazz);
	}

	@Override
	public Page getAllByPage(Class clazz, Pageable page) {
		return dao.getAllByPage(clazz, page);
	}

	@Override
	public T get(Serializable id, Class clazz) {
		return (T) dao.get(id, clazz);
	}

	@Override
	public List findByMoreFiled(Class clazz, LinkedHashMap map) {
		return dao.findByMoreFiled(clazz, map); 
	}

	@Override
	public boolean update(Class entity) {
		return dao.update(entity);
	}

	@Override
	public boolean delete(Class entity) {
		return dao.delete(entity);
	}

	@Override
	public boolean save(Class entity) {
		return dao.save(entity); 
	}
	

}
