package com.redsun.service;

import java.util.List;

import com.redsun.entities.Role;
import com.redsun.entities.RolePermissionMenu;

public interface RoleService {
	public List<Role> getAll(Boolean bInluceChild) throws Exception;

	public int add(Role role) throws Exception;

	public int edit(Role role) throws Exception;
	
	public int delete(int roleId) throws Exception;

	public int deleteRolePermissionMenuList(int roleId) throws Exception;
	
	public int addRolePermissionMenuList(List<RolePermissionMenu> menus) throws Exception;

	public int addRolePermissionMenu(RolePermissionMenu menus) throws Exception;

	public Role getRoleById(int roleId) throws Exception;
}
