package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.ProjectMaterialsDao;
import com.redsun.service.dao.mapper.ProjectMaterialsMapper;
import com.redsun.service.entities.ProjectMaterials;

@Repository
public class ProjectMaterialsDaoImpl implements ProjectMaterialsDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(ProjectMaterialsDaoImpl.class);

	
	private static String ADD_PROJECT_MATERIAL = "INSERT INTO project_materials (project_id, material_id, quantity, net_price, price, client_id) VALUES (?, ?, ?, ?, ?, ?)";
	private static String UPDATE_PROJECT_MATERIAL = "UPDATE project_materials SET quantity=?, net_price=?, price=? WHERE project_id = ? AND material_id = ? AND client_id = ?";
	private static String SELECT_ALL_PROJECT_MATERIAL = "SELECT pa.id, pa.project_id, pa.material_id, u.name as unit, pa.quantity, pa.net_price, pa.price, pa.client_id, "
			+ "m.name AS material_name, m.description FROM project_materials AS pa "
			+ "INNER JOIN materials AS m ON pa.material_id = m.id INNER JOIN units AS u ON u.id = m.unit WHERE pa.project_id = ? AND pa.client_id = ? ORDER BY material_name";
	private final static String SQL_SELECT_FILTER = "(SELECT pm.id, pm.project_id, pm.material_id, m.name as material_name, u.name as unit, pm.quantity, pm.net_price, pm.price, pm.client_id, m.description " 
			+ "FROM project_materials as pm INNER JOIN materials as m ON m.id = pm.material_id INNER JOIN units AS u ON u.id = m.unit WHERE pm.project_id = ?) " 
			+ "EXCEPT " 
			+ "(SELECT pm.id, pm.project_id, pm.material_id, m.name as material_name, u.name as unit, pm.quantity, pm.net_price, pm.price, pm.client_id, m.description " 
			+ "FROM project_materials as pm INNER JOIN materials as m ON m.id = pm.material_id INNER JOIN units AS u ON u.id = m.unit INNER JOIN task_materials AS tm "
			+ "ON tm.material_id = pm.material_id WHERE tm.task_id = ? and tm.client_id = ?)";
	private static String SELECT_BY_PROJECTID_MATERIALID = "SELECT pa.id, pa.project_id, pa.material_id, u.name as unit, pa.quantity, pa.net_price, pa.price, pa.client_id, "
			+ "m.name AS material_name, m.unit, m.description FROM project_materials AS pa "
			+ "INNER JOIN materials AS m ON pa.material_id = m.id INNER JOIN units AS u ON u.id = m.unit WHERE pa.project_id = ? AND pa.material_id = ? AND pa.client_id = ?";

	private static String DELETE_MATERIAL = "DELETE FROM project_materials WHERE id = ?";
	@Override
	public List<ProjectMaterials> getByProjectId(int projectId, int clientId) {
		List<ProjectMaterials> listProjectMaterials = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);

		try {
			listProjectMaterials = jdbcTemplate.query(SELECT_ALL_PROJECT_MATERIAL, params.toArray(),
					new ProjectMaterialsMapper());
			return listProjectMaterials;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProjectMaterials;
	}

	@Override
	public int insert(List<ProjectMaterials> listProjectMaterials) {
		int result = 0;
		for (ProjectMaterials projectMaterials : listProjectMaterials) {
			
			result = jdbcTemplate.update(ADD_PROJECT_MATERIAL,
					new Object[] { 
							projectMaterials.getProjectId(),
							projectMaterials.getMaterialId(),
							projectMaterials.getQuantity(),
							projectMaterials.getNetPrice(),
							projectMaterials.getPrice(),
							projectMaterials.getClientId()});
		}
		return result;
	}

	@Override
	public int update(ProjectMaterials projectMaterials) {
		int result = jdbcTemplate.update(UPDATE_PROJECT_MATERIAL,
				new Object[] {
						projectMaterials.getQuantity(),
						projectMaterials.getNetPrice(),
						projectMaterials.getPrice(),
						projectMaterials.getProjectId(),
						projectMaterials.getMaterialId(),
						projectMaterials.getClientId()});
		return result;
	}

	@Override
	public List<ProjectMaterials> filterProjectMaterials(int projectId, int taskId, int clientId) {
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(taskId);
		params.add(clientId);
		List<ProjectMaterials> result = jdbcTemplate.query(SQL_SELECT_FILTER, params.toArray(), new ProjectMaterialsMapper());
		return result;
	}
	
	@Override
	public List<ProjectMaterials> getByProjectIdAndMaterialId(int projectId, int materialId, int clientId) {
		Object[] primaryKey = new Object[] { projectId, materialId, clientId };
		return jdbcTemplate.query(SELECT_BY_PROJECTID_MATERIALID, primaryKey, new ProjectMaterialsMapper());
	}

	@Override
	public int updatePMaterial(ProjectMaterials projectMaterials) {

		int result = 0;
		try {
			String UPDATE_HR = "UPDATE project_materials "
					+ "SET net_price=?, price=?, quantity=?  WHERE id = " + projectMaterials.getId();

			result = jdbcTemplate.update(UPDATE_HR,
					new Object[] {
							projectMaterials.getNetPrice(),
							projectMaterials.getPrice(),
							projectMaterials.getQuantity()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int delete(int projectMaterialId) {
		int result = jdbcTemplate.update(DELETE_MATERIAL, new Object[] { projectMaterialId });
		return result;
	}
	
}
