package com.redsun.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.dao.UserDao;
import com.redsun.dao.mapper.UserMenuMapper;
import com.redsun.dao.mapper.UserRowMapper;
import com.redsun.entities.Menu;
import com.redsun.entities.User;
import com.redsun.service.auth.MyUserDetails;
import com.redsun.utils.UserUtil;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	UserUtil userUtil;
	
	private static Logger log = Logger.getLogger(UserDaoImpl.class);

	private String GET_USER_BY_NAME = "select u.username, u.password, r.name role_name, u.client_id, u.create_by, u.create_date, u.status from Users u left join users_roles ul on u.username=ul.username left join roles r on ul.role_id=r.id where u.username = ? and u.status = ?::bit";
	private String GET_MENU_BY_USERNAME = "select m.id, m.name, m.description, m.url, m.position, m.iconclass, rpm.permission, ul.create_by from menus m left join role_permission_menu rpm on m.id= rpm.menu_id left join users_roles ul on ul.role_id = rpm.role_id where ul.username = ? and rpm.permission like '%1%' order by position ASC";
	private String ADD_USER = "insert into Users(username, password,profile_id, client_id, create_by, status) values(?,md5(?::text),?,?,?,?::bit)";
	private String EDIT_USER = "Update Users set password=md5(?::text), update_by=?, update_date=?::date where username=? and client_id=? and status=?::bit";
	private String CHANGE_PASS = "Update Users set password=md5(?::text), update_by=?, update_date=?::date where username=? and password=md5(?::text) and client_id=? and status=?::bit";
	private String EDIT_USER_ROLE = "Update users_roles set role_id=?, update_by=?, update_date=?::date where username=? and client_id=?";
	private String ADD_USER_ROLE = "insert into users_roles(role_id, username, client_id, create_by) values(?,?,?,?)";
	private String DELETE_USER = "update Users set status=?::bit where username=? and client_id=?";
	private String GET_USER = "select u.username, u.password, ul.role_id role_name, u.client_id, u.create_by, u.create_date, u.status from Users u left join users_roles ul on u.username=ul.username where u.username = ? and u.status = ?::bit and u.client_id=?";
	private String GET_ALL_USERS = "select u.username, u.password, r.name role_name, u.client_id, u.create_by, u.create_date, u.status from Users u left join users_roles ul on u.username=ul.username left join roles r on ul.role_id=r.id where u.client_id=? AND status=?::bit and u.username !=?";
	
	private String GET_PROJECT_ROLE_ID_BY_USERNAME_AND_PROJECT_ID = "select module_permission_id from module_role where username=? AND module_permission_key=?";
	
	public int addUser(User user) throws Exception{
		try{
			int result = jdbcTemplate.update(ADD_USER, new Object[]{user.getUsername(), user.getPassword(),user.getProfileId(), user.getClientId(), user.getCreatedBy(), UserUtil.ENABLED_USER});
			if(result > 0){
				jdbcTemplate.update(ADD_USER_ROLE, new Object[]{Integer.valueOf(user.getRole()), user.getUsername(),user.getClientId(),user.getCreatedBy()});
			}
			return result;
		} catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}
	}
	
	public int editUser(User user) throws Exception{
		try{
			int result = jdbcTemplate.update(EDIT_USER, new Object[]{user.getPassword(), user.getUpdatedBy(), "now()", user.getUsername(), user.getClientId(), UserUtil.ENABLED_USER});
			if(result > 0){
				jdbcTemplate.update(EDIT_USER_ROLE, new Object[]{Integer.valueOf(user.getRole()),user.getUpdatedBy(),"now()", user.getUsername(),user.getClientId()});
			}
			return result;
		} catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}
	}
	
	public int deleteUser(String username, int clientId) throws Exception{
		try{
			return jdbcTemplate.update(DELETE_USER, new Object[]{UserUtil.DISABLED_USER, username, clientId});
		} catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}
	}
	
	public User getUserByUsername(String username) throws Exception{
		try {
			List<User> list = null;
			list = jdbcTemplate.query(GET_USER_BY_NAME, new Object[] { username, UserUtil.ENABLED_USER }, new UserRowMapper());
			if (list != null && list.size() == 1) {
				log.info("LOGIN: " + username);
				return list.get(0);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return null;
	}

	public List<Menu> getMenusByUsername(String username) throws Exception{
		List<Menu> listMenu = null;

		try {
			listMenu = jdbcTemplate.query(GET_MENU_BY_USERNAME, new Object[] { username }, new UserMenuMapper());
			return listMenu;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	public List<User> getAllUsers(int clientId) throws Exception {
		try {
			return jdbcTemplate.query(GET_ALL_USERS, new Object[] { clientId, UserUtil.ENABLED_USER, userUtil.getLoginedUsername()}, new UserRowMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	public User getUser(String username, int clientId) throws Exception {
		try {
			List<User> list = null;
			list = jdbcTemplate.query(GET_USER, new Object[] { username, UserUtil.ENABLED_USER, clientId }, new UserRowMapper());
			if (list != null && list.size() == 1) {
				log.info("LOGIN: " + username);
				return list.get(0);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return null;
	}

	public int changePassword(MyUserDetails user, String currentPass, String newPass) throws Exception{
		try{
			int result = jdbcTemplate.update(CHANGE_PASS, new Object[]{newPass, user.getUsername(), "now()", user.getUsername(), currentPass, user.getClientId(), UserUtil.ENABLED_USER});
			return result;
		} catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int getModuleRoleIdByUsername(String username, int projectId) {
		int projectRoleId = 0;
		try{
			projectRoleId = jdbcTemplate.queryForObject(GET_PROJECT_ROLE_ID_BY_USERNAME_AND_PROJECT_ID, new Object[] { username, "project_"+projectId }, Integer.class);
		} catch (Exception e) {
			log.warn("Just warning for user with out role in project assigned");
		}
		
		return projectRoleId;
	}

}
