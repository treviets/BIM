package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Teams;

/**
 * Teams Mapper
 */
@Component
public class TeamsRowMapper implements RowMapper<Teams> {

	public Teams mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Teams teams = new Teams();
		teams.setId(rs.getInt("id"));
		if(rs.wasNull()) { teams.setId(null); };
		teams.setName(rs.getString("name"));
		teams.setDescription(rs.getString("description"));
		teams.setIdle(rs.getInt("idle"));
		teams.setIdResource(rs.getInt("id_resource"));
		if(rs.wasNull()) { teams.setIdResource(null); };
		teams.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { teams.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			teams.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return teams;
	}
}

