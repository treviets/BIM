package com.redsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.dao.UserDao;
import com.redsun.entities.User;
import com.redsun.service.UserService;
import com.redsun.service.auth.MyUserDetails;
import com.redsun.utils.UserUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	UserUtil userUtil;
	
	public List<User> getAllUsers() throws Exception {
		return userDao.getAllUsers(userUtil.getClientIdOfLoginedUser());
	}

	public int addUser(User user) throws Exception {
		user.setClientId(userUtil.getClientIdOfLoginedUser());
		user.setCreatedBy(userUtil.getLoginedUsername());
		return userDao.addUser(user);
	}
	
	public int deleteUser(String username) throws Exception {
		return userDao.deleteUser(username, userUtil.getClientIdOfLoginedUser());
	}

	public User getUser(String username) throws Exception {
		return userDao.getUser(username, userUtil.getClientIdOfLoginedUser());
	}
	public int editUser(User user) throws Exception {
		user.setClientId(userUtil.getClientIdOfLoginedUser());
		user.setUpdatedBy(userUtil.getLoginedUsername());
		return userDao.editUser(user);
	}
	public int changePassword (String currentPass, String newPass) throws Exception {
		MyUserDetails user = userUtil.getLoginedUser();
		return userDao.changePassword(user, currentPass, newPass);
	}

}
