package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.ProjectEquipmentsDao;
import com.redsun.service.dao.mapper.ProjectEquipmentsMapper;
import com.redsun.service.entities.ProjectEquipments;

@Repository
public class ProjectEquipmentsDaoImpl implements ProjectEquipmentsDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(ProjectEquipmentsDaoImpl.class);


	private static String ADD_PROJECT_EQUIPMENT = "INSERT INTO project_equipments (project_id, equipment_id, quantity, net_price, price, client_id) VALUES (?, ?, ?, ?, ?, ?)";
	private static String SELECT_ALL_PROJECT_EQUIPMENT = "SELECT pe.id, pe.project_id, pe.equipment_id, u.name as unit, pe.quantity, pe.net_price, pe.price, pe.client_id, "
			+ "e.name AS equipment_name, e.description "
			+ "FROM project_equipments AS pe "
			+ "INNER JOIN equipments AS e ON pe.equipment_id = e.id "
			+ "INNER JOIN units AS u ON u.id = e.unit "
			+ "WHERE pe.project_id = ? AND pe.client_id = ? ORDER BY equipment_name";
	private final static String SQL_SELECT_FILTER = "(SELECT pe.id, pe.project_id, pe.equipment_id, pe.quantity, e.name as equipment_name, u.name as unit, pe.net_price, pe.price, pe.client_id, e.description " 
			+ "FROM project_equipments as pe INNER JOIN equipments as e ON e.id = pe.equipment_id INNER JOIN units AS u ON u.id = e.unit WHERE pe.project_id = ?) " 
			+ "EXCEPT " 
			+ "(SELECT pe.id, pe.project_id, pe.equipment_id, pe.quantity, e.name as equipment_name, u.name as unit, pe.net_price, pe.price, pe.client_id, e.description " 
			+ "FROM project_equipments as pe INNER JOIN equipments as e ON e.id = pe.equipment_id INNER JOIN units AS u ON u.id = e.unit INNER JOIN task_equipments AS te "
			+ "ON te.equipment_id = pe.equipment_id WHERE te.task_id = ? and te.client_id = ?)";
	private static String SELECT_BY_PROJECTID_EQUIPMENTID = "SELECT pa.id, pa.project_id, pa.equipment_id, u.name as unit, pa.quantity, pa.net_price, pa.price, pa.client_id, e.description, "
			+ "e.name AS equipment_name FROM project_equipments AS pa "
			+ "INNER JOIN equipments AS e ON pa.equipment_id = e.id INNER JOIN units AS u ON u.id = e.unit WHERE pa.project_id = ? AND pa.equipment_id = ? AND pa.client_id = ?";
	
	private static String DELETE_EQUIPMENT = "DELETE FROM project_equipments WHERE id = ?";
	@Override
	public List<ProjectEquipments> getByProjectId(int projectId, int clientId) {
		List<ProjectEquipments> listProjectEquipments = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);

		try {
			listProjectEquipments = jdbcTemplate.query(SELECT_ALL_PROJECT_EQUIPMENT, params.toArray(),
					new ProjectEquipmentsMapper());
			return listProjectEquipments;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProjectEquipments;
	}

	@Override
	public int insert(List<ProjectEquipments> listProjectEquipments) {
		int result = 0;
		for (ProjectEquipments projectEquipments : listProjectEquipments) {
			
			result = jdbcTemplate.update(ADD_PROJECT_EQUIPMENT,
					new Object[] { 
							projectEquipments.getProjectId(),
							projectEquipments.getEquipmentId(),
							projectEquipments.getQuantity(),
							projectEquipments.getNetPrice(),
							projectEquipments.getPrice(),
							projectEquipments.getClientId()});
		}
		return result;
	}

	@Override
	public List<ProjectEquipments> filterProjectEquipments(int projectId, int taskId, int clientId) {
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(taskId);
		params.add(clientId);
		List<ProjectEquipments> result = jdbcTemplate.query(SQL_SELECT_FILTER, params.toArray(), new ProjectEquipmentsMapper());
		return result;
	}
	
	@Override
	public List<ProjectEquipments> getByProjectIdAndEquipmentId(int projectId, int equipmentId, int clientId) {
		Object[] primaryKey = new Object[] { projectId, equipmentId, clientId };
		return jdbcTemplate.query(SELECT_BY_PROJECTID_EQUIPMENTID, primaryKey, new ProjectEquipmentsMapper());
	}
	@Override
	public int update(ProjectEquipments projectEquipments) {

		int result = 0;
		try {
			String UPDATE_HR = "UPDATE project_equipments "
					+ "SET net_price=?, price=?, quantity=?  WHERE id = " + projectEquipments.getId();

			result = jdbcTemplate.update(UPDATE_HR,
					new Object[] {
							projectEquipments.getNetPrice(),
							projectEquipments.getPrice(),
							projectEquipments.getQuantity()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}


	@Override
	public int delete(int projectEquipmentsId) {
		int result = jdbcTemplate.update(DELETE_EQUIPMENT, new Object[] { projectEquipmentsId });
		return result;
	}
}