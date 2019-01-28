package com.xiaozl.learn.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.persistence.EntityManager;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.beanutils.BeanFactory;
import org.apache.commons.configuration.beanutils.DefaultBeanFactory;
import org.hibernate.validator.constraints.Length;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaozl.learn.common.BaseController;
import com.xiaozl.learn.entity1.User;
import com.xiaozl.learn.entity2.TestData;
import com.xiaozl.learn.pojo.MatcheType;
import com.xiaozl.learn.pojo.ParamMatcher;
import com.xiaozl.learn.service.ICrudService;

/**
 * @author Mr.shawn
 *
 */
@RestController
public class TestDataSourceController extends BaseController{

	@Autowired
	ICrudService crudService;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/test1")
	public Object  get() {
		User user = (User) crudService.get(1, User.class,true);  
		
		TestData user2 = (TestData) crudService.get(1, TestData.class,false);  
		
		Map map = new HashMap<>();
		map.put("id", new ParamMatcher(MatcheType.EQUALS, "1"));
		map.put("name", new ParamMatcher(MatcheType.LIKE, "s"));
		List findByMoreFiled = crudService.findByMoreFiled(TestData.class, map, false); 
		return findByMoreFiled; 
	}
	
	
	/**
	 * try to understand the structure of the tree
	 */
	@RequestMapping("/test2")
	public ResponseEntity<Object>  get2() {
		LinkedList list;
		ArrayList list2; Object[] data;
		Collection coll;
		Collections coll2;
//		数组与队列的区别
		String[] o = new String[3];  //char value[]
		
		return null;
	}
	
	public static void main(String[] args) {
		char[] s = {'a','b','c'};
		List asList = Arrays.asList(s); 
		Arrays.sort(s); 
		Collections.sort(asList);
		Collections.reverse(asList); 
		int[] num = {1,2,3};
		long currentTimeMillis = System.currentTimeMillis();
		System.gc() ;
		Runtime runtime ;
 	}
	
	
	
}
