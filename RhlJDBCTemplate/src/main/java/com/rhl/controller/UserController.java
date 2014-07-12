/**
 * 
 */
package com.rhl.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rhl.dal.entity.User;
import com.rhl.service.impl.UserService;
import com.rhl.vo.UserVO;
import com.rhl.vo.Users;

/**
 * @author Rahul Vishwakarma
 * 
 */
@Controller
public class UserController {
	
	static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
	
	
	/**
	 * Create a new user with the User data
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String addUsers(@RequestBody User user, HttpServletResponse response) throws Exception {
		int key = userService.addUserInfo(user);
		String msg ="Created user with ID:"+key;
		log.info(msg);
		return msg;
	}

	/**
	 * Get the list of users
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Users getUsers(HttpServletResponse response) throws Exception {
		Users us = userService.getUserInfo();
		return us;
	}
	
	/**
	 * Fetch the user based on the id or name
	 * @param id
	 * @param response
	 * @return
	 * @throws GenesisException
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public UserVO getUserProfile(@PathVariable String id,HttpServletResponse response) throws Exception {
		UserVO us = null;
		us = userService.getUserInfo(Integer.parseInt(id));
		return us;
	}
	
	/**
	 * Update the user based on the id or name
	 * @param id
	 * @param response
	 * @return
	 * @throws GenesisException
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
	public String updateUserProfile(@PathVariable String id, @RequestBody User us, HttpServletResponse response) throws Exception {
		us.setUserId(Integer.parseInt(id));
		userService.updateUserInfo(us);
		String info = "User Updated with Id "+id;
		return info;
	}
	
	
	/**
	 * Delete user based on the id or name
	 * @param id
	 * @param response
	 * @return
	 * @throws GenesisException
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public String deleteUserProfile(@PathVariable String id,HttpServletResponse response) throws Exception {
		userService.deleteUserInfo(Integer.parseInt(id));
		String info = "User Updated with Id "+id;
		return info;
	}
	

	
}
