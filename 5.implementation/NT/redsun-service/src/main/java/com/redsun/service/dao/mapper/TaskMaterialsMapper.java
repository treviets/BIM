package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.TaskMaterials;

@Component
public class TaskMaterialsMapper implements RowMapper<TaskMaterials> {
	@Autowired
	private TaskMaterials taskMaterial;

	public TaskMaterials mapRow(ResultSet rs, int rowNum) throws SQLException {
		taskMaterial = new TaskMaterials();
		taskMaterial.setId(rs.getInt("id"));
		taskMaterial.setProjectId(rs.getInt("project_id"));
		taskMaterial.setTaskId(rs.getInt("task_id"));
		taskMaterial.setProjectMaterialId(rs.getInt("material_id"));
		taskMaterial.setNetPrice(rs.getDouble("net_price"));
		taskMaterial.setPrice(rs.getDouble("price"));
		taskMaterial.setMaterialName(rs.getString("material_name"));
		taskMaterial.setUnit(rs.getString("unit"));
		taskMaterial.setDescription(rs.getString("description"));
		taskMaterial.setQuantity(rs.getDouble("estimate_quantity"));
		taskMaterial.setActualQuantity(rs.getDouble("actual_quantity"));
		taskMaterial.setClientId(rs.getInt("client_id"));
		return taskMaterial;
	}

}
