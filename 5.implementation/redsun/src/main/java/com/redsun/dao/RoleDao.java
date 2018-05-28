package com.redsun.dao;

import java.util.List;

import com.redsun.entities.Role;
import com.redsun.entities.RolePermissionMenu;

public interface RoleDao {
	public Role get(int roleId) throws Exception;
	public Role getRoleById(int roleId) throws Exception;
	public int add(Role role) throws Exception;
	public int edit(Role role) throws Exception;
	public int delete(int roleId) throws Exception;	
	public List<Role> getAll(Boolean bInluceChild) throws Exception;
	public int addRolePermissionMenu(RolePermissionMenu menu) throws Exception;
	RolePermissionMenu get(int roleId, int menuId) throws Exception;
	public int editRolePermissionMenu(RolePermissionMenu menu) throws Exception;
	List<RolePermissionMenu> getAllRolePermissionMenu(int roleId) throws Exception;
	public int deleteRolePermissionMenu(int roleId);
}
