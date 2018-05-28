package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Healths;

/**
 * Healths Mapper
 */
@Component
public class HealthsRowMapper implements RowMapper<Healths> {

	public Healths mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Healths healths = new Healths();
		healths.setId(rs.getInt("id"));
		if(rs.wasNull()) { healths.setId(null); };
		healths.setName(rs.getString("name"));
		healths.setColor(rs.getString("color"));
		healths.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { healths.setSortOrder(null); };
		healths.setIdle(rs.getInt("idle"));
		healths.setIcon(rs.getString("icon"));
		healths.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { healths.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			healths.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return healths;
	}
}

