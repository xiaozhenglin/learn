package com.xiaozl.learn.service.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xiaozl.learn.dao.ICrudDao;
import com.xiaozl.learn.pojo.ParamMatcher;
import com.xiaozl.learn.service.ICrudService;

@Service
public class CrudServiceImpl<T> implements ICrudService{
	
	@Autowired
	private ICrudDao<T> dao;

	@Override
	public List getAll(Class clazz,boolean isPramDataSource) {
		if(isPramDataSource) {
			dao.setDataSource("entityManagerPrimary");
		}else {
			dao.setDataSource("entityManagerSecondary");
		}
		return dao.getAll(clazz);
	}

	@Override
	public Page getAllByPage(Class clazz, Pageable page,boolean isPramDataSource) {
		if(isPramDataSource) {
			dao.setDataSource("entityManagerPrimary");
		}else {
			dao.setDataSource("entityManagerSecondary");
		}
		return dao.getAllByPage(clazz, page);
	}

	@Override
	public T get(Serializable id, Class clazz,boolean isPramDataSource) {
		if(isPramDataSource) {
			dao.setDataSource("entityManagerPrimary");
		}else {
			dao.setDataSource("entityManagerSecondary");
		}
		return (T) dao.get(id, clazz);
	}

	@Override
	public List findByMoreFiled(Class clazz, Map map,boolean isPramDataSource) {
		if(isPramDataSource) {
			dao.setDataSource("entityManagerPrimary");
		}else {
			dao.setDataSource("entityManagerSecondary");
		}
		return dao.findByMoreFiled(clazz, map); 
	}

	@Override
	public boolean update(Class entity,boolean isPramDataSource) {
		if(isPramDataSource) {
			dao.setDataSource("entityManagerPrimary");
		}else {
			dao.setDataSource("entityManagerSecondary");
		}
		return dao.update(entity);
	}

	@Override
	public boolean delete(Class entity,boolean isPramDataSource) {
		if(isPramDataSource) {
			dao.setDataSource("entityManagerPrimary");
		}else {
			dao.setDataSource("entityManagerSecondary");
		}
		return dao.delete(entity);
	}

	@Override
	public boolean save(Class entity,boolean isPramDataSource) {
		if(isPramDataSource) {
			dao.setDataSource("entityManagerPrimary");
		}else {
			dao.setDataSource("entityManagerSecondary");
		}
		return dao.save(entity); 
	}
	

}
