package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.TaskMaterialsDao;
import com.redsun.service.dao.mapper.TaskMaterialsMapper;
import com.redsun.service.entities.TaskMaterials;

@Repository
public class TaskMaterialsDaoImpl implements TaskMaterialsDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(TaskMaterialsDaoImpl.class);

	private static String SELECT_ALL_TASK_MATERIAL = "SELECT ta.id, ta.project_id, ta.task_id, ta.material_id, pm.net_price, pm.price, ta.estimate_quantity, ta.actual_quantity, ta.client_id," 
			+" m.name as material_name, m.description, "
			+" u.name as unit "
			+" FROM task_materials as ta "
			+" LEFT JOIN materials as m ON ta.material_id = m.id "
			+" LEFT JOIN units as u ON u.id = m.unit "
			+" LEFT JOIN project_materials as pm ON ta.material_id = pm.material_id "
			+" WHERE ta.task_id = ? AND pm.project_id = ? AND ta.client_id = ?";

	private static String ADD_TASK_MATERIAL = "INSERT INTO task_materials (project_id, task_id, material_id, estimate_quantity, client_id ) VALUES (?, ?, ?, ?, ?)";
	private static String UPDATE_TASK_MATERIAL = "UPDATE task_materials SET actual_quantity=? WHERE id = ?";
	private static String DELETE_TASK_MATERIAL = "DELETE FROM task_materials WHERE id = ?";
	@Override
	public List<TaskMaterials> getByTask(int taskId, int projectId, int clientId) {
		List<TaskMaterials> listTaskMaterials = null;
		List<Object> params = new ArrayList<Object>();
		params.add(taskId);
		params.add(projectId);
		params.add(clientId);

		try {
			listTaskMaterials = jdbcTemplate.query(SELECT_ALL_TASK_MATERIAL, params.toArray(),
					new TaskMaterialsMapper());
			return listTaskMaterials;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listTaskMaterials;
	}

	@Override
	public int insert(List<TaskMaterials> listTaskMaterials) {
		int result = 0;
		for (TaskMaterials taskMaterial : listTaskMaterials) {
			
			result = jdbcTemplate.update(ADD_TASK_MATERIAL,
					new Object[] { 
							taskMaterial.getProjectId(),
							taskMaterial.getTaskId(),
							taskMaterial.getProjectMaterialId(),
							taskMaterial.getQuantity(),
							taskMaterial.getClientId()
							});
		}
		return result;
	}

	@Override
	public int update(TaskMaterials taskMaterial) {
		int result = 0;
			
			result = jdbcTemplate.update(UPDATE_TASK_MATERIAL,
					new Object[] { 
							taskMaterial.getActualQuantity(),
							taskMaterial.getId(),
							});
		return result;
	}

	@Override
	public int updateTaskMaterials(TaskMaterials taskMaterials) {
		int result = 0;
		try {
			String UPDATE_TASK_MATERIAL = "UPDATE task_materials "
					+ "SET estimate_quantity=?  WHERE id = " + taskMaterials.getId();

			result = jdbcTemplate.update(UPDATE_TASK_MATERIAL,
					new Object[] {
							taskMaterials.getQuantity()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int delete(int taskMaterialsId) {
		int result = jdbcTemplate.update(DELETE_TASK_MATERIAL, new Object[] { taskMaterialsId });
		return result;
	}
}
