package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.ProjectEquipments;

@Component
public class ProjectEquipmentsMapper implements RowMapper<ProjectEquipments> {
	@Autowired
	private ProjectEquipments projectEquipment;

	public ProjectEquipments mapRow(ResultSet rs, int rowNum) throws SQLException {
		projectEquipment = new ProjectEquipments();
		projectEquipment.setId(rs.getInt("id"));
		projectEquipment.setProjectId(rs.getInt("project_id"));
		projectEquipment.setEquipmentId(rs.getInt("equipment_id"));
		projectEquipment.setQuantity(rs.getInt("quantity"));
		projectEquipment.setEquipmentName(rs.getString("equipment_name"));
		projectEquipment.setUnit(rs.getString("unit"));
		projectEquipment.setDescription(rs.getString("description"));
		projectEquipment.setNetPrice(rs.getDouble("net_price"));
		projectEquipment.setPrice(rs.getDouble("price"));
		projectEquipment.setClientId(rs.getInt("client_id"));
		return projectEquipment;
	}

}
