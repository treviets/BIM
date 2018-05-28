package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.ProjectMaterials;

@Component
public class ProjectMaterialsMapper implements RowMapper<ProjectMaterials> {
	@Autowired
	private ProjectMaterials projectMaterial;

	public ProjectMaterials mapRow(ResultSet rs, int rowNum) throws SQLException {
		projectMaterial = new ProjectMaterials();
		projectMaterial.setId(rs.getInt("id"));
		projectMaterial.setProjectId(rs.getInt("project_id"));
		projectMaterial.setMaterialId(rs.getInt("material_id"));
		projectMaterial.setMaterialName(rs.getString("material_name"));
		projectMaterial.setUnit(rs.getString("unit"));
		projectMaterial.setDescription(rs.getString("description"));
		projectMaterial.setQuantity(rs.getDouble("quantity"));
		projectMaterial.setNetPrice(rs.getDouble("net_price"));
		projectMaterial.setPrice(rs.getDouble("price"));
		projectMaterial.setClientId(rs.getInt("client_id"));
		return projectMaterial;
	}

}
