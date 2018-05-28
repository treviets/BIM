package com.redsun.doc.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.doc.entities.Directory;

@Component
public class DirectoryMapper implements RowMapper<Directory> {
	
	@Autowired
	private Directory directory;

	public Directory mapRow(ResultSet rs, int rowNum) throws SQLException {

		directory = new Directory();
		directory.setId(rs.getInt("id"));
		directory.setName(rs.getString("name"));
		directory.setLocation(rs.getString("location"));
		directory.setParentId(rs.getInt("parent_id"));
		directory.setProjectId(rs.getInt("project_id"));
		directory.setClientId(rs.getInt("client_id"));
		directory.setCreateDate(rs.getDate("create_date_time"));
		directory.setUpdateDate(rs.getDate("update_date_time"));
		directory.setCreateUserName(rs.getString("create_user_name"));
		return directory;
	}
}
