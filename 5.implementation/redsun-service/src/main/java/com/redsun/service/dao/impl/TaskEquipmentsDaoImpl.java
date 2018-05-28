package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.TaskEquipmentsDao;
import com.redsun.service.dao.mapper.TaskEquipmentsMapper;
import com.redsun.service.entities.TaskEquipments;

@Repository
public class TaskEquipmentsDaoImpl implements TaskEquipmentsDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(TaskEquipmentsDaoImpl.class);

	private static String SELECT_ALL_TASK_EQUIPMENT = "SELECT te.id, te.project_id, te.task_id, te.equipment_id, te.quantity, te.actual_work, te.client_id, "
			+ "e.name as equipment_name, e.description, pe.net_price, pe.price, "
			+ "u.name as unit "
			+ "FROM task_equipments as te "
			+ " LEFT JOIN project_equipments pe ON pe.equipment_id = te.equipment_id "
			+ "	LEFT JOIN equipments as e ON te.equipment_id = e.id "
			+ "	LEFT JOIN units as u ON u.id = e.unit "
			+ "WHERE te.task_id = ? AND pe.project_id = ? AND te.client_id = ?";

	private static String ADD_TASK_EQUIPMENT = "INSERT INTO task_equipments (project_id, task_id, equipment_id, quantity, client_id ) VALUES (?, ?, ?, ?, ?)";
	private static String DELETE_TASK_EQUIPMENT = "DELETE FROM task_equipments WHERE id = ?";
	@Override
	public List<TaskEquipments> getByTask(int taskId, int projectId, int clientId) {
		List<TaskEquipments> listTaskEquipments = null;
		List<Object> params = new ArrayList<Object>();
		params.add(taskId);
		params.add(projectId);
		params.add(clientId);

		try {
			listTaskEquipments = jdbcTemplate.query(SELECT_ALL_TASK_EQUIPMENT, params.toArray(),
					new TaskEquipmentsMapper());
			return listTaskEquipments;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listTaskEquipments;
	}

	@Override
	public int insert(List<TaskEquipments> listTaskEquipments) {
		int result = 0;
		for (TaskEquipments taskEquipments : listTaskEquipments) {
			
			result = jdbcTemplate.update(ADD_TASK_EQUIPMENT,
					new Object[] { 
							taskEquipments.getProjectId(),
							taskEquipments.getTaskId(),
							taskEquipments.getEquipmentId(),
							taskEquipments.getQuantity(),
							taskEquipments.getClientId()
							});
		}
		return result;
	}

	@Override
	public int updateTaskEquipments(TaskEquipments taskEquipments) {
		int result = 0;
		try {
			String UPDATE_TASK_EQUIPMENT = "UPDATE task_equipments "
					+ "SET quantity=?, actual_work = ?  WHERE id = ?";

			result = jdbcTemplate.update(UPDATE_TASK_EQUIPMENT,
					new Object[] {
							taskEquipments.getQuantity(),
							taskEquipments.getActualWork(),
							taskEquipments.getId(),
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int delete(int taskEquipmentsId) {
		int result = jdbcTemplate.update(DELETE_TASK_EQUIPMENT, new Object[] { taskEquipmentsId });
		return result;
	}
}
