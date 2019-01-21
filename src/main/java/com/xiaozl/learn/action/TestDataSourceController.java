package com.xiaozl.learn.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	ICrudService crudSerice;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/test1")
	public Object  get() {
		User user = (User) crudSerice.get(1, User.class,true);  
		
		TestData user2 = (TestData) crudSerice.get(1, TestData.class,false);  
		
		Map map = new HashMap<>();
		map.put("ID", new ParamMatcher(MatcheType.EQUALS, "1"));
		map.put("NAME", new ParamMatcher(MatcheType.LIKE, "s"));
		List findByMoreFiled = crudSerice.findByMoreFiled(TestData.class, map, false); 
		return user2; 
	}
	
	
	/**
	 * try to understand the structure of the tree
	 * @return
	 */
	@RequestMapping("/test2")
	public ResponseEntity<Object>  get2() {
		LinkedList list;
		ArrayList list2; Object[] data;
		Collection coll;
		Collections coll2;
		return null;
	}
	
	
}
