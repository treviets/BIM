package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.TaskMaterialTracking;

public class TaskMaterialTrackingMapper implements RowMapper<TaskMaterialTracking> {
	@Autowired
	private TaskMaterialTracking taskMaterialTracking;

	public TaskMaterialTracking mapRow(ResultSet rs, int rowNum) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");

		taskMaterialTracking = new TaskMaterialTracking();
		taskMaterialTracking.setId(rs.getInt("id"));
		taskMaterialTracking.setTaskId(rs.getInt("task_id"));
		taskMaterialTracking.setProjectId(rs.getInt("project_id"));
		taskMaterialTracking.setActualMaterial(rs.getDouble("actual_material"));
		taskMaterialTracking.setDateLog(rs.getDate("date_log"));
		if (taskMaterialTracking.getDateLog() != null)
			taskMaterialTracking.setStringDateLog(sdf.format(taskMaterialTracking.getDateLog()));
		taskMaterialTracking.setOwner(rs.getString("owner"));
		taskMaterialTracking.setMaterialId(rs.getInt("material_id"));
		taskMaterialTracking.setNetPrice(rs.getDouble("net_price"));
		taskMaterialTracking.setPrice(rs.getDouble("price"));
		return taskMaterialTracking;
	}

}
