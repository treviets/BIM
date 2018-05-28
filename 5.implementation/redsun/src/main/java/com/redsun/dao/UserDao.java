package com.redsun.dao;

import java.util.List;

import com.redsun.entities.Menu;
import com.redsun.entities.User;
import com.redsun.service.auth.MyUserDetails;

public interface UserDao {
	public User getUserByUsername(String username)throws Exception;
	public List<Menu> getMenusByUsername(String username)throws Exception;
	public int addUser(User user) throws Exception;
	public int editUser(User user) throws Exception;
	public int deleteUser(String username, int clientId) throws Exception;
	public User getUser(String username, int clientId) throws Exception;
	public List<User> getAllUsers(int clientId) throws Exception;
	public int changePassword(MyUserDetails user, String currentPass, String newPass) throws Exception;
	public int getModuleRoleIdByUsername(String username, int projectId);
}
