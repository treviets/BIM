package com.redsun.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.entities.ModulePermission;
import com.redsun.entities.ModuleProperty;

public class ModulePermissionRowMapper implements RowMapper<ModulePermission>{

	@Override
	public ModulePermission mapRow(ResultSet rs, int rowNum) throws SQLException {
		ModulePermission mp = new ModulePermission();
		mp.setId(rs.getInt("id"));
		mp.setKey(rs.getString("key"));
		mp.setName(rs.getString("name"));
		
		ModuleProperty mpr = new ModuleProperty();
		mpr.setItem(rs.getString("module_item"));
		
		mp.setModuleProperty(mpr);
		mp.setPermission(rs.getString("permission"));
		
		return mp;
	}

}
