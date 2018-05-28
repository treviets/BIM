package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.OverallProgresses;

/**
 * OverallProgresses Mapper
 */
@Component
public class OverallProgressesRowMapper implements RowMapper<OverallProgresses> {

	public OverallProgresses mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		OverallProgresses overallProgresses = new OverallProgresses();
		overallProgresses.setId(rs.getInt("id"));
		if(rs.wasNull()) { overallProgresses.setId(null); };
		overallProgresses.setName(rs.getString("name"));
		overallProgresses.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { overallProgresses.setSortOrder(null); };
		overallProgresses.setIdle(rs.getInt("idle"));
		overallProgresses.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { overallProgresses.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			overallProgresses.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return overallProgresses;
	}
}

