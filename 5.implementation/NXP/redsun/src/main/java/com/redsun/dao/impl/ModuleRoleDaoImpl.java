package com.redsun.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.dao.ModuleRoleDao;
import com.redsun.dao.mapper.ModuleRoleRowMapper;
import com.redsun.entities.ModuleRole;

@Repository
public class ModuleRoleDaoImpl implements ModuleRoleDao {

	public static Logger log = Logger.getLogger(ModulePropertyDaoImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;
	private String GET = "select id, username, module_permission_id, module_permission_key from module_role where username = ? and module_permission_key = ?";
	private String UPDATE = "update module_role set username = ?, module_permission_id = ? where id = ?";
	private String DELETE = "delete module_role where id = ?";
	private String ADD = "insert into module_role(username, module_permission_id, module_permission_key) values(?,?,?)";
	private String GET_BY_KEY ="select id, username, module_permission_id, module_permission_key from module_role where module_permission_key = ?";
	@Override
	public int update(ModuleRole role) throws Exception {
		return jdbcTemplate.update(UPDATE,
				new Object[] { role.getUsername(), role.getModulePermission().getId(), role.getId() });
	}

	@Override
	public int delete(int id) throws Exception {
		try {
			return jdbcTemplate.update(DELETE, new Object[] { id });
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public ModuleRole getModuleRole(String username, String modulePermissionKey) throws Exception {
		try {
			List<ModuleRole> list = jdbcTemplate.query(GET, new Object[] { username, modulePermissionKey },
					new ModuleRoleRowMapper());
			if (list.size() > 0) {
				return list.get(0);
			}
			return null;

		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int addModuleRole(ModuleRole role) throws Exception {
		return jdbcTemplate.update(ADD, new Object[] { role.getUsername(), role.getModulePermission().getId(),
				role.getModulePermission().getKey() });
	}

	@Override
	public List<ModuleRole> getModuleRoles(String modulePermissionKey) throws Exception {
		try {
			List<ModuleRole> list = jdbcTemplate.query(GET_BY_KEY, new Object[] { modulePermissionKey },
					new ModuleRoleRowMapper());
			return list;

		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
}
