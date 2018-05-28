package com.redsun.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.entities.ModulePermission;
import com.redsun.entities.ModuleRole;

public class ModuleRoleRowMapper implements RowMapper<ModuleRole>{

	@Override
	public ModuleRole mapRow(ResultSet rs, int rowNum) throws SQLException {
		ModuleRole role = new ModuleRole();
		ModulePermission pm = new ModulePermission();
		role.setId(rs.getInt("id"));
		role.setUsername(rs.getString("username"));
		
		pm.setId(rs.getInt("module_permission_id"));
		pm.setKey(rs.getString("module_permission_key"));
		
		role.setModulePermission(pm);
		
		return role;
	}

}
