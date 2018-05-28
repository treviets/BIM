package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.CronExecutions;

/**
 * CronExecutions Mapper
 */
@Component
public class CronExecutionsRowMapper implements RowMapper<CronExecutions> {

	public CronExecutions mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		CronExecutions cronExecutions = new CronExecutions();
		cronExecutions.setId(rs.getInt("id"));
		if(rs.wasNull()) { cronExecutions.setId(null); };
		cronExecutions.setCron(rs.getString("cron"));
		cronExecutions.setFileExecuted(rs.getString("file_executed"));
		cronExecutions.setIdle(rs.getInt("idle"));
		if(rs.wasNull()) { cronExecutions.setIdle(null); };
		cronExecutions.setFonctionName(rs.getString("fonction_name"));
		cronExecutions.setNextTime(rs.getString("next_time"));
		cronExecutions.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { cronExecutions.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			cronExecutions.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return cronExecutions;
	}
}

