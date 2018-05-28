package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Referencables;

/**
 * Referencables Mapper
 */
@Component
public class ReferencablesRowMapper implements RowMapper<Referencables> {

	public Referencables mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Referencables referencables = new Referencables();
		referencables.setId(rs.getInt("id"));
		if(rs.wasNull()) { referencables.setId(null); };
		referencables.setName(rs.getString("name"));
		referencables.setIdle(rs.getInt("idle"));
		referencables.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { referencables.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			referencables.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return referencables;
	}
}

