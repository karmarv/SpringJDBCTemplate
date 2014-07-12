package com.rhl.service;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.rhl.dal.entity.User;
import com.rhl.service.impl.UserService;
import com.rhl.vo.UserVO;
import com.rhl.vo.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
									"/Spring-Module.xml" })
public class UserServiceTest {

	/** Logger for this class and subclasses */
	private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);
	
	@Autowired
	private UserService userService;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test Batch insert
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testCreateBatch() throws Exception {
		List<User> users = new ArrayList<>();
		users.add(new User("RV", "R", "V", "sfe@sf", "vsr", "VD", "efesw", 1));
		users.add(new User("RAV", "AR", "AV", "wesfe@sf", "vwsefsr", "VDS", "ceeefesw", 1));
		
		ObjectMapper om = new ObjectMapper();
		log.info("Adding Users>>> "+om.writeValueAsString(users));
		int res = userService.addUserInfoBatch(users);
		log.info("Created user batch with batch count "+res);
	}

	/**
	 * Test the CRUD operation at the service level
	 * @throws Exception
	 */
	//@Ignore
	@Test
	public void test() throws Exception{
		log.info("Create a User");
		int key = testCreate();
		log.info("Get all User");
		testRead();
		log.info("Update User "+key);
		testUpdate(key);
		log.info("Get all User");
		testRead();
		log.info("Delete User "+key);
		testDelete(key);
		log.info("Count Users ");
		testCount();
	}

	public int testCreate() throws Exception {
		User u = new User();
		u.setUsername("R");
		u.setFirstName("A");
		u.setLastName("B");
		u.setPassword("X");
		u.setEmail("@");
		u.setEnabled(1);
		u.setOrganization("YYY");
		u.setPhone("666");
		
		ObjectMapper om = new ObjectMapper();
		log.info("Adding User>>> "+om.writeValueAsString(u));
		int res = userService.addUserInfo(u);
		log.info("Created user with id "+res);
		return res;
	}

	public void testRead() throws Exception {
		Users u = userService.getUserInfo();
		log.info("Read all users: "+u);
		
		UserVO us = userService.getUserInfoBeanProperty(1);
		log.info("Read user 1 bean property row "+ us);
	}

	public void testUpdate(int key) throws Exception {
		User u = new User();
		u.setUserId(key);
		u.setUsername("Rh");
		u.setFirstName("AB");
		u.setLastName("BC");
		u.setPassword("XY");
		u.setEmail("@");
		u.setEnabled(1);
		u.setOrganization("YYX");
		u.setPhone("66");
		String res = userService.updateUserInfo(u);
		log.info(res);
	}
	
	public void testDelete(int key) throws Exception {
		boolean status = userService.deleteUserInfo(key);
		log.info("Deleted user "+key+" with status "+status);
	}
	
	public void testCount() throws Exception {
		int count = userService.countUserInfo();
		log.info("User Count " + count);
	}
}
