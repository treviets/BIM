package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.TaskResourceTracking;

public class TaskResourceTrackingMapper implements RowMapper<TaskResourceTracking> {
	@Autowired
	private TaskResourceTracking taskResourceTracking;

	public TaskResourceTracking mapRow(ResultSet rs, int rowNum) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");

		taskResourceTracking = new TaskResourceTracking();
		taskResourceTracking.setId(rs.getInt("id"));
		taskResourceTracking.setTaskId(rs.getInt("task_id"));
		taskResourceTracking.setProjectId(rs.getInt("project_id"));
		taskResourceTracking.setActualTime(rs.getInt("actual_time"));
		taskResourceTracking.setOverTime(rs.getInt("over_time"));
		taskResourceTracking.setCoefficient(rs.getDouble("coefficient"));
		taskResourceTracking.setDateLog(rs.getDate("date_log"));
		if (taskResourceTracking.getDateLog() != null)
			taskResourceTracking.setStringDateLog(sdf.format(taskResourceTracking.getDateLog()));
		taskResourceTracking.setOwner(rs.getString("owner"));
		taskResourceTracking.setQuantity(rs.getInt("quantity"));
		taskResourceTracking.setResourceId(rs.getInt("resource_id"));
		taskResourceTracking.setSalary(rs.getDouble("salary"));
		return taskResourceTracking;
	}

}
