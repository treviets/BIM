package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.ProjectResources;

@Component
public class ProjectResourcesMapper implements RowMapper<ProjectResources> {
	@Autowired
	private ProjectResources projectResources;

	public ProjectResources mapRow(ResultSet rs, int rowNum) throws SQLException {
		projectResources = new ProjectResources();
		projectResources.setId(rs.getInt("id"));
		projectResources.setResourceId(rs.getInt("resource_id"));
		projectResources.setSalary(rs.getDouble("salary"));
		projectResources.setQuantity(rs.getInt("quantity"));
		projectResources.setResourceCode(rs.getString("resource_code"));
		projectResources.setResourceName(rs.getString("resource_name"));
		projectResources.setEmailOfResponsible(rs.getString("email"));
		projectResources.setProjectId(rs.getInt("project_id"));
		projectResources.setClientId(rs.getInt("client_id"));
		return projectResources;
	}

}
