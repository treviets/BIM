package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.DocumentVersions;

/**
 * DocumentVersions Mapper
 */
@Component
public class DocumentVersionsMapper implements RowMapper<DocumentVersions> {

	public DocumentVersions mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		DocumentVersions documentVersions = new DocumentVersions();
		documentVersions.setId(rs.getInt("id"));
		documentVersions.setName(rs.getString("name"));
		documentVersions.setProjectId(rs.getInt("project_id"));
		documentVersions.setTaskId(rs.getInt("task_id"));
		documentVersions.setProjectName(rs.getString("project_name"));
		documentVersions.setDocumentId(rs.getInt("document_id"));
		documentVersions.setDocumentName(rs.getString("document_name"));
		documentVersions.setVersion(rs.getInt("version"));
		documentVersions.setAuthorName(rs.getString("author_name"));
		documentVersions.setLink(rs.getString("link"));
		documentVersions.setDescription(rs.getString("description"));
		documentVersions.setCreateDateTime(rs.getDate("create_date_time"));
		documentVersions.setUpdateDate(rs.getDate("update_date_time"));
		documentVersions.setTotalCount(rs.getInt("total_count"));
		documentVersions.setClientId(rs.getInt("client_id"));

		return documentVersions;
	}
}
