package com.redsun.dao;

import java.util.List;

import com.redsun.entities.ModuleRole;

public interface ModuleRoleDao {

	public int update(ModuleRole role) throws Exception;

	public int delete(int id) throws Exception;

	public ModuleRole getModuleRole(String username, String modulePermissionKey) throws Exception;

	public int addModuleRole(ModuleRole role) throws Exception;

	public List<ModuleRole> getModuleRoles(String modulePermissionKey) throws Exception;
}
