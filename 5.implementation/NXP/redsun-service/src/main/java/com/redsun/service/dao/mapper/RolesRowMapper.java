package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Roles;

/**
 * Roles Mapper
 */
@Component
public class RolesRowMapper implements RowMapper<Roles> {

	public Roles mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Roles roles = new Roles();
		roles.setId(rs.getInt("id"));
		if(rs.wasNull()) { roles.setId(null); };
		roles.setName(rs.getString("name"));
		roles.setDescription(rs.getString("description"));
		roles.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { roles.setSortOrder(null); };
		roles.setIdle(rs.getInt("idle"));
		roles.setDefaultCost(rs.getBigDecimal("default_cost"));
		if(rs.wasNull()) { roles.setDefaultCost(null); };
		roles.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { roles.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			roles.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return roles;
	}
}

