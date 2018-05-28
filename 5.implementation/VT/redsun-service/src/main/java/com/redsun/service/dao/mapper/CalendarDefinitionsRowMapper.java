package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.CalendarDefinitions;

/**
 * CalendarDefinitions Mapper
 */
@Component
public class CalendarDefinitionsRowMapper implements RowMapper<CalendarDefinitions> {

	public CalendarDefinitions mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		CalendarDefinitions calendarDefinitions = new CalendarDefinitions();
		calendarDefinitions.setId(rs.getInt("id"));
		if(rs.wasNull()) { calendarDefinitions.setId(null); };
		calendarDefinitions.setName(rs.getString("name"));
		calendarDefinitions.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { calendarDefinitions.setSortOrder(null); };
		calendarDefinitions.setIdle(rs.getInt("idle"));
		calendarDefinitions.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { calendarDefinitions.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			calendarDefinitions.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return calendarDefinitions;
	}
}

