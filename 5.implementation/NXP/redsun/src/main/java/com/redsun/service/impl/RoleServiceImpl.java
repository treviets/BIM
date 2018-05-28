package com.redsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.dao.RoleDao;
import com.redsun.entities.Role;
import com.redsun.entities.RolePermissionMenu;
import com.redsun.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;

	@Override
	public List<Role> getAll(Boolean bInluceChild) throws Exception {
		return roleDao.getAll(bInluceChild);
	}

	@Override
	public int add(Role role) throws Exception {
		return roleDao.add(role);
	}

	@Override
	public int delete(int roleId) throws Exception {

		return roleDao.delete(roleId);
	}

	@Override
	public int addRolePermissionMenuList(List<RolePermissionMenu> menus) throws Exception {
		for(int i = 0; i < menus.size(); i++){
			try{
				addRolePermissionMenu(menus.get(i));
			}catch(Exception e){
				throw e;
			}
		}
		return menus.size();
	}
	
	@Override
	public int deleteRolePermissionMenuList(int roleId) throws Exception {
		return roleDao.deleteRolePermissionMenu(roleId);
	}

	@Override
	public int addRolePermissionMenu(RolePermissionMenu menu) throws Exception {
		return roleDao.addRolePermissionMenu(menu);
		
	}

	@Override
	public Role getRoleById(int roleId) throws Exception {
		// TODO Auto-generated method stub
		return roleDao.getRoleById(roleId);
	}

	@Override
	public int edit(Role role) throws Exception {
		return roleDao.edit(role);
	}

}
