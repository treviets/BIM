package com.redsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.dao.ModulePermissionDao;
import com.redsun.dao.ModulePropertyDao;
import com.redsun.dao.ModuleRoleDao;
import com.redsun.entities.ModulePermission;
import com.redsun.entities.ModuleProperty;
import com.redsun.entities.ModuleRole;
import com.redsun.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	ModulePropertyDao modulePropertiesDao;

	@Autowired
	ModulePermissionDao modulePermissionDao;
	
	@Autowired 
	ModuleRoleDao moduleRoleDao;
	@Override
	public List<ModuleProperty> getModuleProperties(String moduleName) throws Exception {
		return modulePropertiesDao.gets(moduleName);
	}

	@Override
	public List<ModulePermission> getListModulePermission(String name, String key) throws Exception {
		return modulePermissionDao.getListModulePermission(name, key);
	}

	@Override
	public int addModulePermission(ModulePermission mp) throws Exception {
		return modulePermissionDao.addModulePermission(mp);
	}

	@Override
	public int updateModulePermission(ModulePermission mp) throws Exception {
		return modulePermissionDao.updateModulePermission(mp);
	}

	@Override
	public List<ModulePermission> getListModulePermission(String key) throws Exception {
		return modulePermissionDao.getListModulePermission(key);
	}

	@Override
	public ModulePermission getModulePermission(int id) throws Exception {
		return modulePermissionDao.getListModulePermission(id);
	}

	@Override
	public int deleteModulePermission(int id) throws Exception {
		return modulePermissionDao.deleteModulePermission(id);
	}

	@Override
	public int deleteModulePermission(String name, String key) throws Exception {
		return modulePermissionDao.deleteModulePermission(name, key);
	}

	@Override
	public int updateModuleRole(ModuleRole role) throws Exception {
		return moduleRoleDao.update(role);
	}

	@Override
	public int deleteModuleRole(int id) throws Exception {
		return moduleRoleDao.delete(id);
	}

	@Override
	public ModuleRole getModuleRole(String username, String modulePermissionKey) throws Exception {
		return moduleRoleDao.getModuleRole(username, modulePermissionKey);
	}

	@Override
	public int addModuleRole(ModuleRole role) throws Exception {
		return moduleRoleDao.addModuleRole(role);
	}

	@Override
	public List<ModuleRole> getModuleRoles(String modulePermissionKey) throws Exception {
		return moduleRoleDao.getModuleRoles(modulePermissionKey);
	}

}
