package com.redsun.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.dao.RoleDao;
import com.redsun.dao.mapper.RoleMapper;
import com.redsun.dao.mapper.RolePermissionMenuMapper;
import com.redsun.dao.mapper.UserMenuMapper;
import com.redsun.dao.mapper.UserRowMapper;
import com.redsun.entities.Menu;
import com.redsun.entities.Role;
import com.redsun.entities.RolePermissionMenu;
import com.redsun.entities.User;
import com.redsun.utils.UserUtil;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired	
	JdbcTemplate jdbcTemplate;

	@Autowired
	UserUtil userUtil;

	private static Logger log = Logger.getLogger(UserDaoImpl.class);

	private String GET_USER_BY_NAME = "select u.username, u.password, r.name role_name, u.client_id, u.create_by, u.create_date, u.status from Users u left join users_roles ul on u.username=ul.username left join roles r on ul.role_id=r.id where u.username = ? and u.status = ?::bit";
	private String GET_MENU_BY_USERNAME = "select m.id, m.name, m.description, m.content, m.position, rpm.permission from menus m left join role_permission_menu rpm on m.id= rpm.menu_id left join users_roles ul on ul.role_id = rpm.role_id where ul.username = ? order by position ASC";
	private String ADD_ROLE = "insert into Roles(name, description) values(?,?) returning id,name,description";
	private String EDIT_ROLE = "Update roles set name=?, description=? where id=?";
	private String DELETE_USER = "update Users set status=?::bit where username=? and client_id=?";
	private String GET_USER = "select u.username, u.password, ul.role_id role_name, u.client_id, u.create_by, u.create_date, u.status from Users u left join users_roles ul on u.username=ul.username where u.username = ? and u.status = ?::bit and u.client_id=?";
	private String GET_ALL_USERS = "select u.username, u.password, r.name role_name, u.client_id, u.create_by, u.create_date, u.status from Users u left join users_roles ul on u.username=ul.username left join roles r on ul.role_id=r.id where u.client_id=? AND status=?::bit and u.username !=?";
	private String GET_ALL_ROLES = "select id, name, description from roles";
	private String GET_ROLE_BY_ID = "select id, name, description from roles where id=?";
	private String DELETE_ROLE = "delete from roles where id = ?";
	private String GET_ROLE_BY_MID = "select id, role_id, menu_id, permission from role_permission_menu where role_id = ? and menu_id =?";
	private String EDIT_ROLE_PERMISSION = "update role_permission_menu set role_id = ?, menu_id = ?, permission=? where id = ?";
	private String ADD_ROLE_PERMISSION = "insert into role_permission_menu(role_id, menu_id, permission) values(?,?,?)";
	private String DELETE_ROLE_PERMISSION_MENU_BY_ROLE_ID = "delete from role_permission_menu where role_id = ?";
	private String GET_ROLEPR_BY_ROLEID = "select rpm.id, rpm.role_id, rpm.menu_id, rpm.permission, m.name, m.description from role_permission_menu rpm, menus m where rpm.menu_id = m.id and rpm.role_id = ?";

	public int deleteUser(String username, int clientId) throws Exception {
		try {
			return jdbcTemplate.update(DELETE_USER, new Object[] { UserUtil.DISABLED_USER, username, clientId });
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	public User getUserByUsername(String username) throws Exception {
		try {
			List<User> list = null;
			list = jdbcTemplate.query(GET_USER_BY_NAME, new Object[] { username, UserUtil.ENABLED_USER },
					new UserRowMapper());
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

	public List<Menu> getMenusByUsername(String username) throws Exception {
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
			return jdbcTemplate.query(GET_ALL_USERS,
					new Object[] { clientId, UserUtil.ENABLED_USER, userUtil.getLoginedUsername() },
					new UserRowMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	public User getUser(String username, int clientId) throws Exception {
		try {
			List<User> list = null;
			list = jdbcTemplate.query(GET_USER, new Object[] { username, UserUtil.ENABLED_USER, clientId },
					new UserRowMapper());
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

	@Override
	public int add(Role role) throws Exception {
		try {
			List<Role> list = jdbcTemplate.query(ADD_ROLE, new Object[] { role.getName(), role.getDescription() },
					new RoleMapper());
			if (list != null && list.size() == 1) {
				return list.get(0).getId();
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return 0;
	}

	@Override
	public int edit(Role role) throws Exception {
		try {
			int result = jdbcTemplate.update(EDIT_ROLE, new Object[] { role.getName(), role.getDescription(), role.getId()});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int delete(int roleId) throws Exception {
		try {
			int result = jdbcTemplate.update(DELETE_ROLE, new Object[] { roleId });
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Role get(int roleId) throws Exception {
		return null;
	}

	@Override
	public RolePermissionMenu get(int roleId, int menuId) throws Exception {
		try {
			List<RolePermissionMenu> list = null;
			list = jdbcTemplate.query(GET_ROLE_BY_MID, new Object[] { roleId, menuId }, new RolePermissionMenuMapper());
			if (list != null && list.size() == 1) {
				return list.get(0);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}

		return null;
	}

	@Override
	public List<RolePermissionMenu> getAllRolePermissionMenu(int roleId) throws Exception {
		return jdbcTemplate.query(GET_ROLEPR_BY_ROLEID, new Object[] { roleId }, new RolePermissionMenuMapper());
	}

	@Override
	public Role getRoleById(int roleId) throws Exception {
		try {
			List<Role> list = jdbcTemplate.query(GET_ROLE_BY_ID, new Object[]{roleId},  new RoleMapper());
			if(list != null){
				Role roleObject = list.get(0);
				if(roleObject != null){
					roleObject.setMenu(getAllRolePermissionMenu(roleObject.getId()));
				}
				return roleObject;
			}
			return null;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Role> getAll(Boolean bInluceChild) throws Exception {
		try {
			List<Role> list = null;
			list = jdbcTemplate.query(GET_ALL_ROLES, new RoleMapper());

			if (bInluceChild) {
				for (int i = 0; i < list.size(); i++) {
					Role role = list.get(i);
					list.get(i).setMenu(getAllRolePermissionMenu(role.getId()));
				}
			}
			return list;

		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int addRolePermissionMenu(RolePermissionMenu menu) throws Exception {
		try {
			RolePermissionMenu per = get(menu.getRoleId(), menu.getMenuId());
			if (per != null) {
				// update
				editRolePermissionMenu(menu);
			} else {
				return jdbcTemplate.update(ADD_ROLE_PERMISSION,
						new Object[] { menu.getRoleId(), menu.getMenuId(), menu.getPermission() });
			}
		} catch (Exception e) {
			throw e;
		}
		return 0;
	}

	@Override
	public int editRolePermissionMenu(RolePermissionMenu menu) {
		try {
			return jdbcTemplate.update(EDIT_ROLE_PERMISSION,
					new Object[] { menu.getRoleId(), menu.getMenuId(), menu.getPermission(), menu.getId() });
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public int deleteRolePermissionMenu(int role_id) {
		try {
			return jdbcTemplate.update(DELETE_ROLE_PERMISSION_MENU_BY_ROLE_ID,
					new Object[] { role_id});
		} catch (Exception e) {
			throw e;
		}
	}

}
