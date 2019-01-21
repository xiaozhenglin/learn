package com.xiaozl.learn.dao.impl;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.xiaozl.learn.dao.DaoUtil;
import com.xiaozl.learn.dao.ICrudDao;

/**
 * @author xiaozl
 *
 * @param <T>
 */
@Repository
public class CrudDaoImpl<T> implements ICrudDao{
	
	
	@Autowired
	@Qualifier("entityManagerPrimary")
	EntityManager em1;
	
	@Autowired
	@Qualifier("entityManagerSecondary")
	EntityManager em2;
	
	@Autowired
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private DaoUtil util;
	
	//根据类名获取注解表名称
	private String getTableNameByClass(Class clazz) {
		Table annotation = (Table) clazz.getAnnotation(Table.class); 
		return annotation.name();
	}
	
	//根据属性名获取注解列名称
	private String getColumnNameByField(Class clazz,String fieldName) {
		Field declaredField;
		try {
			declaredField = clazz.getDeclaredField(fieldName);
			Column column=declaredField.getAnnotation(Column.class);
			return column.name();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Transactional
    @Override
    public boolean save(Class entity){
    	em.clear();
        boolean flag=false;
        try {
        	em.persist(entity);
            flag=true;
        }catch (Exception e){
            System.out.println("---------------保存出错---------------");
            throw e;
        }
        em.close();
        return flag;
    }

	@Override
	public List<T> getAll(Class clazz) {
		em.clear();
		String sqlString = "select * from "+getTableNameByClass(clazz);
		List resultList = em.createNativeQuery(sqlString, clazz).getResultList();
		em.close();
		return resultList;
	}
	
	@Override
	public PageImpl getAllByPage(Class clazz, Pageable page) {
		em.clear();
		String sqlString = "select * from "+getTableNameByClass(clazz);
		PageImpl queryPage = (PageImpl) util.queryPage(sqlString, null, clazz, page);
		em.close();
		return queryPage;
	}
	
	@Override
	public Object get(Serializable id, Class clazz) {
		em.clear();
		Object find = em.find(clazz, id);
		em.close();
		return find;
	}
	
	@Override
	public List findByMoreFiled(Class clazz, LinkedHashMap map) {
		em.clear();
	    String sql=" from "+getTableNameByClass(clazz)+" u WHERE ";
        Set<String> set=null;
        set=map.keySet();
        List<String> list=new ArrayList<>(set);
//        List<Object> filedlist=new ArrayList<>();
        for (String filed:list){
            sql+=" u."+getColumnNameByField(clazz,filed)+"=? and ";
//            filedlist.add(filed);
        }
        Query query=em.createQuery(sql);
        for (int i=0;i<list.size();i++){
            query.setParameter(i+1,map.get(list.get(i)));
        }
        List<T> listResult= query.getResultList();
        em.close();
        return listResult;
	}
	

	
    @Transactional
    @Override
    public boolean update(Class entity) {
    	em.clear();
        boolean flag = false;
        try {
            em.merge(entity);
            flag = true;
        } catch (Exception e) {
            System.out.println("---------------更新出错---------------");
        }
        em.close();
        return flag;
    }
	
    @Transactional
    @Override
    public boolean delete(Class entity) {
    	em.clear();
        boolean flag=false;
        try {
        	em.remove(entity);
            flag=true;
        }catch (Exception e){
            System.out.println("---------------删除出错---------------");
        }
        em.close();
        return flag;
    }
}
