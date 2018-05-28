package com.redsun.social.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.social.entities.FileSharing;

@Component
public class FileSharingMapper implements RowMapper<FileSharing> {

	@Autowired
	private FileSharing fileSharing;

	public FileSharing mapRow(ResultSet rs, int rowNum) throws SQLException {

		fileSharing = new FileSharing();
		fileSharing.setId(rs.getInt("id"));
		fileSharing.setClientId(rs.getInt("client_id"));
		fileSharing.setName(rs.getString("name"));
		fileSharing.setCreatedBy(rs.getString("created_by"));
		fileSharing.setCreatedDate(rs.getString("created_date"));
		fileSharing.setDescription(rs.getString("description"));
		fileSharing.setFilePath(rs.getString("file_path"));
		fileSharing.setProjectId(rs.getInt("project_id"));
		fileSharing.setGroupId(rs.getInt("group_id"));
		fileSharing.setScopes(rs.getInt("scopes"));
		fileSharing.setTitle(rs.getString("title"));

		return fileSharing;
	}
}
