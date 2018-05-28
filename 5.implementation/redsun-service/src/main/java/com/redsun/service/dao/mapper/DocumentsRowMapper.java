package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Documents;

/**
 * Documents Mapper
 */
@Component
public class DocumentsRowMapper implements RowMapper<Documents> {

	public Documents mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Documents documents = new Documents();
		documents.setId(rs.getInt("id"));
		if(rs.wasNull()) { documents.setId(null); };
		documents.setName(rs.getString("name"));
		documents.setProjectId(rs.getInt("project_id"));
		documents.setTaskId(rs.getInt("task_id"));
		documents.setProjectName(rs.getString("project_name"));
		documents.setTypeId(rs.getInt("type_id"));
		documents.setStatusId(rs.getInt("status_id"));
		documents.setAuthorName(rs.getString("author_name"));
		documents.setFileName(rs.getString("file_name"));
		documents.setDescription(rs.getString("description"));
		documents.setCreationDate(rs.getDate("creation_date"));
		documents.setClientId(rs.getInt("client_id"));
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			documents.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return documents;
	}
}

