package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.Project;

public class ProjectMapper implements RowMapper<Project> {
	@Autowired
	private Project project;

	public Project mapRow(ResultSet rs, int rowNum) throws SQLException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
		project = new Project();
		project.setId(rs.getInt("id"));
		project.setName(rs.getString("name"));
		project.setCode(rs.getString("code"));
		project.setDescription(rs.getString("description"));
		project.setParentId(rs.getInt("parent_id"));
		project.setClientId(rs.getInt("client_id"));
		project.setStartDate(rs.getDate("start_date"));
		if (project.getStartDate() != null)
			project.setStringStartDate(sdf.format(project.getStartDate()));
		project.setAmount(rs.getBigDecimal("amount"));
		project.setTotalCount(rs.getInt("total_count"));
		return project;
	}
}
