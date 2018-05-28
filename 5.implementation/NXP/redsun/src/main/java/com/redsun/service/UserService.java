package com.redsun.service;

import java.util.List;

import com.redsun.entities.User;

public interface UserService {
	public List<User> getAllUsers() throws Exception;
	public int addUser(User user) throws Exception;
	public int editUser(User user) throws Exception;
	public User getUser(String username) throws Exception;
	public int deleteUser(String username) throws Exception;
	public int changePassword(String currentPass, String newPass) throws Exception;
}
