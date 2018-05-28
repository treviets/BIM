package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.VersionProjects;

/**
 * VersionProjects Mapper
 */
@Component
public class VersionProjectsRowMapper implements RowMapper<VersionProjects> {

	public VersionProjects mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		VersionProjects versionProjects = new VersionProjects();
		versionProjects.setId(rs.getInt("id"));
		if(rs.wasNull()) { versionProjects.setId(null); };
		versionProjects.setIdProject(rs.getInt("id_project"));
		if(rs.wasNull()) { versionProjects.setIdProject(null); };
		versionProjects.setIdVersion(rs.getInt("id_version"));
		if(rs.wasNull()) { versionProjects.setIdVersion(null); };
		versionProjects.setStartDate(rs.getDate("start_date"));
		versionProjects.setEndDate(rs.getDate("end_date"));
		versionProjects.setIdle(rs.getInt("idle"));
		versionProjects.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { versionProjects.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			versionProjects.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return versionProjects;
	}
}

