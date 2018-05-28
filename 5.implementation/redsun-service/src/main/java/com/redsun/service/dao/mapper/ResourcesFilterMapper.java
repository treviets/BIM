package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Resources;

/**
 * ResourcesFilter Mapper
 */
@Component
public class ResourcesFilterMapper implements RowMapper<Resources> {

	public Resources mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Resources resources = new Resources();
		resources.setId(rs.getInt("id"));
		resources.setName(rs.getString("name"));
		resources.setCode(rs.getString("code"));
		resources.setSalary(rs.getDouble("salary"));
		resources.setIsTrash(rs.getInt("is_trash"));
		resources.setClientId(rs.getInt("client_id"));

		return resources;
	}
}

