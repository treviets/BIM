package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Documents;

/**
 * Documents Mapper
 */
@Component
public class DocumentDetailMapper implements RowMapper<Documents> {

	@Autowired
	Documents documents;
	
	public Documents mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
		documents = new Documents();

		documents.setId(rs.getInt("id"));
		documents.setName(rs.getString("name"));
		documents.setProjectId(rs.getInt("project_id"));
		documents.setTaskId(rs.getInt("task_id"));
		documents.setProjectName(rs.getString("project_name"));
		documents.setTypeId(rs.getInt("type_id"));
		documents.setTypeName(rs.getString("type_name"));
		documents.setStatusId(rs.getInt("status_id"));
		documents.setStatusName(rs.getString("status_name"));
		documents.setAuthorName(rs.getString("author_name"));
		documents.setCreationDate(rs.getDate("create_date_time"));
		documents.setDescription(rs.getString("description"));
		if (documents.getCreationDate() != null)
			documents.setStringCreationDate(sdf.format(documents.getCreationDate()));
		documents.setClientId(rs.getInt("client_id"));
		documents.setTotalCount(rs.getInt("total_count"));

		return documents;
	}
}

