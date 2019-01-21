package com.xiaozl.learn.dao.impl;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.xiaozl.learn.common.DaoUtil;
import com.xiaozl.learn.common.SpringUtil;
import com.xiaozl.learn.dao.ICrudDao;
import com.xiaozl.learn.pojo.MatcheType;
import com.xiaozl.learn.pojo.ParamMatcher;


/**
 * @author shawn
 *
 * @param <T>
 */
@Repository
public class CrudDaoImpl<T> implements ICrudDao{
	
	
	private static final String ParamMatcher = null;

//	@Autowired
//	@Qualifier("entityManagerPrimary")
//	EntityManager em1;
//	
//	@Autowired
//	@Qualifier("entityManagerSecondary")
//	EntityManager em2;
	
//	@Autowired
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
        return flag;
    }

	@Override
	public List<T> getAll(Class clazz) {
		em.clear();
		String sqlString = "select * from "+getTableNameByClass(clazz);
		List resultList = em.createNativeQuery(sqlString, clazz).getResultList();
		return resultList;
	}
	
	@Override
	public PageImpl getAllByPage(Class clazz, Pageable page) {
		em.clear();
		String sqlString = "select * from "+getTableNameByClass(clazz);
		PageImpl queryPage = (PageImpl) util.queryPage(sqlString, null, clazz, page);
		return queryPage;
	}
	
	@Override
	public Object get(Serializable id, Class clazz) {
		em.clear();
		Object find = em.find(clazz, id);
		return find;
	}
	
	@Override
	public List findByMoreFiled(Class clazz, Map params) {
		em.clear();
	    String sql=" SELECT * FROM "+getTableNameByClass(clazz)+"  WHERE  1=1 ";
        List<String> list=new ArrayList<>(params.keySet());
        for (int i=0;i<list.size();i++){
        	ParamMatcher matcher = (ParamMatcher)params.get(list.get(i));
        	MatcheType type = matcher.getType(); 
        	switch (type) {
			case EQUALS:
	        	sql+=" and "+getColumnNameByField(clazz,list.get(i))+" = ?  ";
				break;
			case EXIST:
	        	sql+=" and "+getColumnNameByField(clazz,list.get(i))+"EXIST ?  ";
				break;
			case LIKE:
	        	sql+=" and "+getColumnNameByField(clazz,list.get(i))+" like '%' ? '%'  ";
				break;
			default:
				break;
			}
        }
        
        Query query=em.createNativeQuery(sql);
        for (int i=0;i<list.size();i++){
        	ParamMatcher matcher = (ParamMatcher)params.get(list.get(i));
        	String value = matcher.getValue(); 
        	query.setParameter(i+1, value);
        }
     
        List<T> listResult= query.getResultList();
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
        return flag;
    }
    
    @Override
    public void setDataSource(String beanName) {
    	this.em = (EntityManager) SpringUtil.getBean(beanName);
    }
    
}
