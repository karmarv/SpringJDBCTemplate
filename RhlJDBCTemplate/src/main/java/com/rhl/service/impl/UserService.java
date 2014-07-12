/**
 * 
 */
package com.rhl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rhl.dal.entity.User;
import com.rhl.dal.impl.UserDAO;
import com.rhl.vo.UserVO;
import com.rhl.vo.Users;

/**
 * @author Rahul Vishwakarma
 *
 */
@Transactional
@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;


	public Users getUserInfo() throws Exception {
		List<User> us = userDAO.getUserInfo();
		List<UserVO> uvos = new ArrayList<UserVO>();
		for(User u : us){
			uvos.add(new UserVO(u));
		}
		//Add to collections
		Users users = new Users(uvos);
		return users;
	}
	
	public UserVO getUserInfo(int userId) throws Exception {
		return new UserVO(userDAO.getUserInfo(userId));
	}
	
	public UserVO getUserInfoBeanProperty(int userId) throws Exception {
		return  new UserVO(userDAO.getUserInfoBeanProperty(userId));
	}
	
	public int addUserInfo(User user) throws Exception {
		return userDAO.addUserInfo(user);
	}
	
	public int addUserInfoBatch(List<User> users) throws Exception {
		return userDAO.addUserInfoBatch(users);
	}

	public String updateUserInfo(User user) throws Exception {
		return userDAO.updateUserInfo(user);
	}
	
	public boolean deleteUserInfo(int userId) throws Exception {
		return userDAO.deleteUserInfo(userId);
	}
	
	public int countUserInfo() throws Exception {
		return userDAO.countUserInfo();
	}
	
}
