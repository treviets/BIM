package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.TaskResourcesDao;
import com.redsun.service.dao.mapper.TaskResouresMapper;
import com.redsun.service.entities.TaskResources;

@Repository
public class TaskResourcesDaoImpl implements TaskResourcesDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(TaskResourcesDaoImpl.class);
	private static String ADD_TASK_RESOURCE = "INSERT INTO task_resources (project_id, resource_id, quantity, warning, task_id, client_id) VALUES (?, ?, ?, ?, ?, ?)"; 
	private static String UPDATE_TASK_RESOURCE =  "UPDATE task_resources SET quantity =?, actual_work = ? WHERE id=?";
	private static String DELETE_TASK_RESOURCE = "DELETE FROM task_resources WHERE id = ?";
	@Override
	public List<TaskResources> getByTask(int taskId, int clientId) {

		String SELECT_ALL_TASK_RESOURCES = "SELECT DISTINCT tr.id, tr.task_id, tr.resource_id, pr.salary, tr.quantity, tr.actual_work, tr.warning, tr.project_id, tr.client_id,"
				+ "p.name as project_name, r.code as resource_code, r.name as resource_name, "
				+ "r.email as email_of_assignee" + " FROM task_resources AS tr "
				+ " left join project_resources pr on tr.project_id = pr.project_id AND tr.resource_id = pr.resource_id"
				+ " left join resources r on tr.resource_id = r.id "
				+ " left join projects p on tr.project_id = p.id "
				+ "WHERE tr.task_id=? AND tr.client_id = ?";
		List<TaskResources> listTaskResources = null;
		List<Object> params = new ArrayList<Object>();
		params.add(taskId);
		params.add(clientId);

		try {
			listTaskResources = jdbcTemplate.query(SELECT_ALL_TASK_RESOURCES, params.toArray(), new TaskResouresMapper());
			return listTaskResources;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listTaskResources;
	}


	@Override
	public int insert(List<TaskResources> listTaskResources) {
		int result = 0;
		for (TaskResources taskResources : listTaskResources) {
			result = jdbcTemplate.update(ADD_TASK_RESOURCE, new Object[]{
					taskResources.getProjectId(),
					taskResources.getResourceId(),
					taskResources.getQuantity(),
					taskResources.getWarning(),
					taskResources.getTaskId(),
					taskResources.getClientId()
			});
		}
		return result;
	}


	@Override
	public int update(TaskResources taskResources) {
		int result = 0;
			result = jdbcTemplate.update(UPDATE_TASK_RESOURCE, new Object[]{
					taskResources.getQuantity(),
					taskResources.getActualWork(),
					taskResources.getId()
			});
		return result;
	}


	@Override
	public List<TaskResources> getTaskResourceOneProject(int projectId, int clientId) {

		String SELECT_TASK_RESOURCES_ONE_PROJECT = "SELECT distinct tr.id, tr.task_id, tr.resource_id, pr.salary, tr.quantity, tr.actual_work, tr.warning, tr.project_id, tr.client_id,"
				+ "p.name as project_name, r.code as resource_code, r.name as resource_name, "
				+ "r.email as email_of_assignee" + " FROM task_resources AS tr "
				+ "INNER JOIN resources as r ON tr.resource_id = r.id "
				+ "INNER JOIN projects as p ON tr.project_id= p.id "
                + "INNER JOIN project_resources as pr ON pr.project_id = p.id "
				+ "WHERE tr.project_id=? AND tr.client_id = ?";
		List<TaskResources> listTaskResources = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);

		try {
			listTaskResources = jdbcTemplate.query(SELECT_TASK_RESOURCES_ONE_PROJECT, params.toArray(), new TaskResouresMapper());
			return listTaskResources;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listTaskResources;
	}


	@Override
	public int delete(int taskResourcesId) {
		int result = jdbcTemplate.update(DELETE_TASK_RESOURCE, new Object[] { taskResourcesId });
		return result;
	}

}
