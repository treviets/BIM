package com.redsun.doc.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.doc.entities.DirectoryResource;

@Component
public class DirectoryResourceMapper implements RowMapper<DirectoryResource> {
	
	@Autowired
	private DirectoryResource directoryResource;

	public DirectoryResource mapRow(ResultSet rs, int rowNum) throws SQLException {

		directoryResource = new DirectoryResource();
		directoryResource.setId(rs.getInt("id"));
		directoryResource.setDirectoryId(rs.getInt("directory_id"));
		directoryResource.setProjectId(rs.getInt("project_id"));
		directoryResource.setResourceId(rs.getInt("resource_id"));
		//directory.setProjectId(rs.getInt("project_id"));
		//directory.setClientId(rs.getInt("client_id"));
		//directory.setCreateDate(rs.getDate("create_date_time"));
		//directory.setUpdateDate(rs.getDate("update_date_time"));
		//directory.setCreateUserName(rs.getString("create_user_name"));
		return directoryResource;
	}
}
