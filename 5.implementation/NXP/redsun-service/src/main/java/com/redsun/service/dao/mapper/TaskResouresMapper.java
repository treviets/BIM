package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.TaskResources;

@Component
public class TaskResouresMapper implements RowMapper<TaskResources> {
	@Autowired
	private TaskResources taskResources;

	public TaskResources mapRow(ResultSet rs, int rowNum) throws SQLException {
		taskResources = new TaskResources();
		taskResources.setId(rs.getInt("id"));
		taskResources.setResourceId(rs.getInt("resource_id"));
		taskResources.setSalary(rs.getDouble("salary"));
		taskResources.setQuantity(rs.getInt("quantity"));
		taskResources.setActualWork(rs.getInt("actual_work"));
		taskResources.setWarning(rs.getString("warning"));
		taskResources.setResourceCode(rs.getString("resource_code"));
		taskResources.setResourceName(rs.getString("resource_name"));
		taskResources.setEmailOfAssignee(rs.getString("email_of_assignee"));
		taskResources.setProjectId(rs.getInt("project_id"));
		taskResources.setTaskId(rs.getInt("task_id"));
		taskResources.setClientId(rs.getInt("client_id"));
		return taskResources;
	}

}
