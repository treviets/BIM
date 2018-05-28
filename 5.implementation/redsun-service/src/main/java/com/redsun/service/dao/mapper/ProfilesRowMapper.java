package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Profiles;

/**
 * Profiles Mapper
 */
@Component
public class ProfilesRowMapper implements RowMapper<Profiles> {

	public Profiles mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Profiles profiles = new Profiles();
		profiles.setId(rs.getInt("id"));
		if(rs.wasNull()) { profiles.setId(null); };
		profiles.setName(rs.getString("name"));
		profiles.setDescription(rs.getString("description"));
		profiles.setProfileCode(rs.getString("profile_code"));
		profiles.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { profiles.setSortOrder(null); };
		profiles.setIdle(rs.getInt("idle"));
		profiles.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { profiles.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			profiles.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return profiles;
	}
}

