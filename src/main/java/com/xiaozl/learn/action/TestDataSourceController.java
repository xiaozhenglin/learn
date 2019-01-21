package com.xiaozl.learn.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaozl.learn.entity1.User;
import com.xiaozl.learn.entity2.TestData;
import com.xiaozl.learn.pojo.MatcheType;
import com.xiaozl.learn.pojo.ParamMatcher;
import com.xiaozl.learn.service.ICrudService;

@RestController
public class TestDataSourceController {

	@Autowired
	ICrudService crudSerice;
	
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
	
	
	
}
