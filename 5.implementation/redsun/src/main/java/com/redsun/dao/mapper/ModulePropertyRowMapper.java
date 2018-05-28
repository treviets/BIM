package com.redsun.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.entities.ModuleProperty;

public class ModulePropertyRowMapper implements RowMapper<ModuleProperty> {

	@Override
	public ModuleProperty mapRow(ResultSet rs, int rowNum) throws SQLException {
		ModuleProperty module = new ModuleProperty();
		module.setName(rs.getString("name"));
		module.setItem(rs.getString("item"));
		module.setItemTitle(rs.getString("item_title"));
		
		return module;
	}

	
}
