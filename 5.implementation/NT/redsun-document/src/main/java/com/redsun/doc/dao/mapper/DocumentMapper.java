package com.redsun.doc.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.doc.entities.Document;

@Component
public class DocumentMapper implements RowMapper<Document> {
	
	@Autowired
	private Document doc;

	public Document mapRow(ResultSet rs, int rowNum) throws SQLException {

		doc = new Document();
		doc.setId(rs.getInt("id"));
		doc.setName(rs.getString("name"));
		doc.setLocation(rs.getString("location"));
		//directory.setProjectId(rs.getInt("project_id"));
		//directory.setClientId(rs.getInt("client_id"));
		doc.setCreateDateTime(rs.getDate("create_date_time"));
		//directory.setUpdateDate(rs.getDate("update_date_time"));
		//directory.setCreateUserName(rs.getString("create_user_name"));
		return doc;
	}
}
