package com.redsun.social.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.social.entities.Group;


@Component
public class GroupMapper implements RowMapper<Group> {
	
	@Autowired
	private Group group;

	public Group mapRow(ResultSet rs, int rowNum) throws SQLException {

		group = new Group();
		group.setId(rs.getInt("id"));
		group.setClientId(rs.getInt("client_id"));
		group.setProjectId(rs.getInt("project_id"));
		group.setCreatedBy(rs.getString("created_by"));
		group.setCreatedDate(rs.getString("created_date"));
		group.setName(rs.getString("name"));
		group.setDescription(rs.getString("description"));
	
		return group;
	}
}
