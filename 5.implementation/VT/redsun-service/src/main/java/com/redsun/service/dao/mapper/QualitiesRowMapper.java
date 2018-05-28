package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Qualities;

/**
 * Qualities Mapper
 */
@Component
public class QualitiesRowMapper implements RowMapper<Qualities> {

	public Qualities mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Qualities qualities = new Qualities();
		qualities.setId(rs.getInt("id"));
		if(rs.wasNull()) { qualities.setId(null); };
		qualities.setName(rs.getString("name"));
		qualities.setColor(rs.getString("color"));
		qualities.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { qualities.setSortOrder(null); };
		qualities.setIcon(rs.getString("icon"));
		qualities.setIdle(rs.getInt("idle"));
		qualities.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { qualities.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			qualities.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return qualities;
	}
}

