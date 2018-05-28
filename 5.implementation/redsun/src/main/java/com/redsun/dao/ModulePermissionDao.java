package com.redsun.dao;

import java.util.List;

import com.redsun.entities.ModulePermission;

public interface ModulePermissionDao {
	public List<ModulePermission> getListModulePermission(String name, String key) throws Exception;

	public int addModulePermission(ModulePermission mp) throws Exception;

	public int updateModulePermission(ModulePermission mp) throws Exception;

	public List<ModulePermission> getListModulePermission(String key) throws Exception;

	public ModulePermission getListModulePermission(int id) throws Exception;

	public int deleteModulePermission(int id) throws Exception;

	public int deleteModulePermission(String name, String key) throws Exception;
}
