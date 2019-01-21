package com.xiaozl.learn.action;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaozl.learn.entity1.User;
import com.xiaozl.learn.entity2.TestData;
import com.xiaozl.learn.service.ICrudService;

@RestController
public class TestDataSourceController {

	@Autowired
	ICrudService crudSerice;
	
	@RequestMapping("/test1")
	public Object  get() {
		User user = (User) crudSerice.get(1, User.class);  
		return user; 
	}
	
	
	
}
