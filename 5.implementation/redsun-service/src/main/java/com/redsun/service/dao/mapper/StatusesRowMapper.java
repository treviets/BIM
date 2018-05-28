package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Statuses;

/**
 * Statuses Mapper
 */
@Component
public class StatusesRowMapper implements RowMapper<Statuses> {

	public Statuses mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Statuses statuses = new Statuses();
		statuses.setId(rs.getInt("id"));
		if(rs.wasNull()) { statuses.setId(null); };
		statuses.setName(rs.getString("name"));
		statuses.setScope(rs.getString("scope"));
		statuses.setColor(rs.getString("color"));
		statuses.setSortOrder(rs.getInt("sort_order"));
		statuses.setClientId(rs.getInt("client_id"));
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			statuses.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return statuses;
	}
}

