package com.redsun.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.dao.ModulePermissionDao;
import com.redsun.dao.mapper.ModulePermissionRowMapper;
import com.redsun.entities.ModulePermission;

@Repository
public class ModulePermissionDaoImpl implements ModulePermissionDao {
	private static Logger log = Logger.getLogger(ModulePermissionDaoImpl.class);
	private String GET_LIST = "select id, name, key, module_item, permission from module_permission where name = ? and key = ?";
	private String GET_LIST2 = "select id, name, key, module_item, permission from module_permission where key = ?";
	private String ADD = "insert into module_permission(name, key, module_item, permission) values(?,?,?,?)";
	private String UPDATE = "update module_permission set name = ?, module_item = ?, permission = ? where id = ? and key =?";
	private String GET_BY_ID = "select id, name, key, module_item, permission from module_permission where id=?";
	private String DELETE_PERMISSION = "delete from module_permission where id=?";
	private String DELETE_PERMISSION2 = "delete from module_permission where name = ? and key = ?";
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<ModulePermission> getListModulePermission(String name, String key) throws Exception {
		try {
			return jdbcTemplate.query(GET_LIST, new Object[] { name, key }, new ModulePermissionRowMapper());

		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int addModulePermission(ModulePermission mp) throws Exception {
		return jdbcTemplate.update(ADD,
				new Object[] { mp.getName(), mp.getKey(), mp.getModuleProperty().getItem(), mp.getPermission() });
	}

	@Override
	public int updateModulePermission(ModulePermission mp) throws Exception {
		return jdbcTemplate.update(UPDATE, new Object[] { mp.getName(), mp.getModuleProperty().getItem(),
				mp.getPermission(), mp.getId(), mp.getKey() });
	}

	@Override
	public List<ModulePermission> getListModulePermission(String key) throws Exception {
		try {
			return jdbcTemplate.query(GET_LIST2, new Object[] { key }, new ModulePermissionRowMapper());

		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public ModulePermission getListModulePermission(int id) throws Exception {
		try {
			List<ModulePermission> list = jdbcTemplate.query(GET_BY_ID, new Object[] { id }, new ModulePermissionRowMapper());
			if(list.size()>0){
				return list.get(0);
			}
			return null;

		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int deleteModulePermission(int id) throws Exception {
		try {
			return jdbcTemplate.update(DELETE_PERMISSION, new Object[] { id });
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int deleteModulePermission(String name, String key) throws Exception {
		try {
			return jdbcTemplate.update(DELETE_PERMISSION2, new Object[] { name, key });
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
