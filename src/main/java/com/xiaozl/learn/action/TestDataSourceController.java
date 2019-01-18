package com.xiaozl.learn.action;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaozl.learn.entity1.User;
import com.xiaozl.learn.entity2.TestData;

@RestController
public class TestDataSourceController {

	@Autowired
	@Qualifier("entityManagerPrimary")
	EntityManager em1;

	@Autowired
	@Qualifier("entityManager")
	EntityManager em;
	
	@Autowired
	@Qualifier("entityManagerSecondary")
	EntityManager em2;
	
	@RequestMapping("/test1")
	public Object  get1() {
		User find = em1.find(User.class, 1);
		return find; 
	}
	
	@RequestMapping("/test2")
	public Object  get2() {
		TestData find = em2.find(TestData.class, 1);
		return find;
	}
	
	
}
