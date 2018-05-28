package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Versions;

/**
 * Versions Mapper
 */
@Component
public class VersionsRowMapper implements RowMapper<Versions> {

	public Versions mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Versions versions = new Versions();
		versions.setId(rs.getInt("id"));
		if(rs.wasNull()) { versions.setId(null); };
		versions.setIdProduct(rs.getInt("id_product"));
		if(rs.wasNull()) { versions.setIdProduct(null); };
		versions.setIdContact(rs.getInt("id_contact"));
		if(rs.wasNull()) { versions.setIdContact(null); };
		versions.setIdResource(rs.getInt("id_resource"));
		if(rs.wasNull()) { versions.setIdResource(null); };
		versions.setName(rs.getString("name"));
		versions.setDescription(rs.getString("description"));
		versions.setCreationDate(rs.getDate("creation_date"));
		versions.setIdle(rs.getInt("idle"));
		versions.setInitialEisDate(rs.getDate("initial_eis_date"));
		versions.setPlannedEisDate(rs.getDate("planned_eis_date"));
		versions.setRealEisDate(rs.getDate("real_eis_date"));
		versions.setInitialEndDate(rs.getDate("initial_end_date"));
		versions.setPlannedEndDate(rs.getDate("planned_end_date"));
		versions.setRealEndDate(rs.getDate("real_end_date"));
		versions.setIsEis(rs.getInt("is_eis"));
		versions.setScope(rs.getString("scope"));
		versions.setVersionNumber(rs.getString("version_number"));
		versions.setIdUser(rs.getInt("id_user"));
		if(rs.wasNull()) { versions.setIdUser(null); };
		versions.setIdVersionType(rs.getInt("id_version_type"));
		if(rs.wasNull()) { versions.setIdVersionType(null); };
		versions.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { versions.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			versions.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return versions;
	}
}

