package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.redsun.service.entities.ResourceAssignedToProjects;
import org.springframework.jdbc.core.RowMapper;


public class ResourceAssignedToProjectsMapper implements RowMapper<ResourceAssignedToProjects> {

	public ResourceAssignedToProjects mapRow(ResultSet rs, int rowNum) throws SQLException {

		ResourceAssignedToProjects rap = null;
		rap = new ResourceAssignedToProjects();
		rap.setId(rs.getInt("id"));
		rap.setProjectId(rs.getInt("project_id"));
		rap.setResourceId(rs.getInt("resource_id"));
		rap.setTeamId(rs.getInt("team_id"));
		return rap;
	}

}
