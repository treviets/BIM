package com.redsun.service;

import java.util.List;

import com.redsun.entities.ModulePermission;
import com.redsun.entities.ModuleProperty;
import com.redsun.entities.ModuleRole;

public interface ModuleService {
	public List<ModuleProperty> getModuleProperties(String moduleName) throws Exception;

	public ModulePermission getModulePermission(int id) throws Exception;

	public List<ModulePermission> getListModulePermission(String name, String key) throws Exception;

	public int addModulePermission(ModulePermission mp) throws Exception;

	public int updateModulePermission(ModulePermission mp) throws Exception;

	public List<ModulePermission> getListModulePermission(String key) throws Exception;

	public int deleteModulePermission(int id) throws Exception;

	public int deleteModulePermission(String name, String key) throws Exception;

	public int updateModuleRole(ModuleRole role) throws Exception;

	public int deleteModuleRole(int id) throws Exception;

	public ModuleRole getModuleRole(String username, String modulePermissionKey) throws Exception;

	public int addModuleRole(ModuleRole role) throws Exception;

	public List<ModuleRole> getModuleRoles(String modulePermissionKey) throws Exception;

}
