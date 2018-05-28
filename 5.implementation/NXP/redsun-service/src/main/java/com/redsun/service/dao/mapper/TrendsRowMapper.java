package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Trends;

/**
 * Trends Mapper
 */
@Component
public class TrendsRowMapper implements RowMapper<Trends> {

	public Trends mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Trends trends = new Trends();
		trends.setId(rs.getInt("id"));
		if(rs.wasNull()) { trends.setId(null); };
		trends.setName(rs.getString("name"));
		trends.setColor(rs.getString("color"));
		trends.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { trends.setSortOrder(null); };
		trends.setIcon(rs.getString("icon"));
		trends.setIdle(rs.getInt("idle"));
		trends.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { trends.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			trends.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return trends;
	}
}

