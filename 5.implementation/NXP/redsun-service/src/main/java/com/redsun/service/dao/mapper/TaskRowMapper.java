package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.Task;

public class TaskRowMapper implements RowMapper<Task> {
	@Autowired
	private Task task;

	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");

		task = new Task();
		task.setId(rs.getInt("id"));
		task.setTaskName(rs.getString("task_name"));
		task.setWbs(rs.getString("wbs"));
		task.setDescription(rs.getString("description"));
		task.setParentId(rs.getString("parent_id"));
		task.setAffectationId(rs.getInt("id_affectation"));
		task.setStartDate(rs.getDate("start_date"));
		if (task.getStartDate() != null)
			task.setStringStartDate(sdf.format(task.getStartDate()));
		task.setEndDate(rs.getDate("end_date"));
		if (task.getEndDate() != null)
			task.setStringEndDate(sdf.format(task.getEndDate()));
		task.setCreateDate(rs.getDate("create_date"));
		if (task.getCreateDate() != null)
			task.setStringCreateDate(sdf.format(task.getCreateDate()));
		task.setUpdateDate(rs.getDate("update_date"));
		if (task.getUpdateDate() != null)
			task.setStringUpdateDate(sdf.format(task.getUpdateDate()));
		task.setEstimateTime(rs.getInt("estimate_time"));
		task.setActuallyTime(rs.getInt("actually_time"));
		task.setResponsible(rs.getInt("responsible"));
		task.setTaskOwner(rs.getString("task_owner"));
		task.setProjectId(rs.getInt("project_id"));
		task.setPriorityId(rs.getInt("priority_id"));
		task.setStatusId(rs.getInt("status_id"));
		task.setTypeId(rs.getInt("type_id"));
		task.setTotalCount(rs.getInt("total_count"));
		task.setCompleted(rs.getInt("completed"));
		task.setComment(rs.getString("comment"));
		task.setIsTrash(rs.getInt("is_trash"));
		task.setClientId(rs.getInt("client_id"));
		return task;
	}

}
