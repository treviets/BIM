package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.TaskEquipments;

@Component
public class TaskEquipmentsMapper implements RowMapper<TaskEquipments> {
	@Autowired
	private TaskEquipments taskEquipment;

	public TaskEquipments mapRow(ResultSet rs, int rowNum) throws SQLException {
		taskEquipment = new TaskEquipments();
		taskEquipment.setId(rs.getInt("id"));
		taskEquipment.setProjectId(rs.getInt("project_id"));
		taskEquipment.setTaskId(rs.getInt("task_id"));
		taskEquipment.setEquipmentId(rs.getInt("equipment_id"));
		taskEquipment.setEquipmentName(rs.getString("equipment_name"));
		taskEquipment.setUnit(rs.getString("unit"));
		taskEquipment.setDescription(rs.getString("description"));
		taskEquipment.setQuantity(rs.getInt("quantity"));
		taskEquipment.setActualWork(rs.getInt("actual_work"));
		taskEquipment.setNetPrice(rs.getDouble("net_price"));
		taskEquipment.setPrice(rs.getDouble("price"));
		taskEquipment.setClientId(rs.getInt("client_id"));
		return taskEquipment;
	}

}
