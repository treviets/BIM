package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.ProjectResourcesDao;
import com.redsun.service.dao.mapper.ProjectResourcesMapper;
import com.redsun.service.entities.ProjectResources;

@Repository
public class ProjectResourcesDaoImpl implements ProjectResourcesDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(ProjectResourcesDaoImpl.class);
	private static String ADD_PROJECT_RESOURCES = "INSERT INTO project_resources (resource_id, salary, quantity, project_id, client_id) VALUES (?, ?, ?, ?, ?)";
	private static String SELECT_GET_BY_PROJECTID = "SELECT pr.id, pr.project_id, pr.resource_id, pr.salary, pr.quantity, pr.client_id, "
			+ "r.code as resource_code, r.name AS resource_name, r.email FROM project_resources AS pr "
			+ "INNER JOIN resources AS r ON pr.resource_id = r.id "
			+ "INNER JOIN projects AS p ON pr.project_id = p.id "
			+ "WHERE pr.project_id = ? AND pr.client_id = ? ORDER BY resource_name";
	private final static String SQL_SELECT_FILTER = "(SELECT m.id , m.resource_id, m.salary, m.quantity, m.project_id, r.code as resource_code, r.name AS resource_name, r.email, m.client_id "
			+ "FROM project_resources AS m INNER JOIN resources as r ON r.id = m.resource_id WHERE m.project_id = ? ) "
			+ "EXCEPT "
			+ "(SELECT m.id , m.resource_id, m.salary, m.quantity, m.project_id,  r.code as resource_code, r.name AS resource_name, r.email, m.client_id " 
			+ "FROM project_resources AS m INNER JOIN resources as r ON r.id = m.resource_id " 
			+ "INNER JOIN task_resources as pm ON pm.resource_id = m.resource_id WHERE pm.task_id = ? AND pm.client_id =?)";

	private static String SELECT_BY_PROJECTID_RESOURCEID = "SELECT pr.id, pr.project_id, pr.resource_id, pr.salary,  pr.quantity, pr.client_id, "
			+ "r.code as resource_code, r.name AS resource_name, r.email FROM project_resources AS pr "
			+ "INNER JOIN resources AS r ON pr.resource_id = r.id "
			+ "INNER JOIN projects AS p ON pr.project_id = p.id "
			+ "WHERE pr.project_id = ? AND pr.resource_id = ? AND pr.client_id = ?";
	private static String SELECT_GET_ALL = "SELECT pr.id, pr.project_id, pr.resource_id, pr.salary, pr.quantity, pr.client_id, "
			+ "r.code as resource_code, r.name AS resource_name, r.email FROM project_resources AS pr "
			+ "INNER JOIN resources AS r ON pr.resource_id = r.id "
			+ "INNER JOIN projects AS p ON pr.project_id = p.id "
			+ "WHERE pr.client_id = ? ORDER BY resource_name";
	private static String DELETE_HR = "DELETE FROM project_resources WHERE id = ?";
	@Override
	public int insert(List<ProjectResources> projectResource) {
		int result = 0;
		for (ProjectResources project_resources : projectResource) {
			
			 result = jdbcTemplate.update(ADD_PROJECT_RESOURCES,
					new Object[] { 
							project_resources.getResourceId(), 
							project_resources.getSalary(),
							project_resources.getQuantity(), 
							project_resources.getProjectId(), 
							project_resources.getClientId() });
		}
		return result;
	}

	@Override
	public List<ProjectResources> getByProject(int projectId, int clientId) {
		List<ProjectResources> listProjectResources = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);
		try {
			listProjectResources = jdbcTemplate.query(SELECT_GET_BY_PROJECTID, params.toArray(), new ProjectResourcesMapper());
			return listProjectResources;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProjectResources;
	}

	@Override
	public List<ProjectResources> filterProjectResources(int projectId, int taskId, int clientId) {
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(taskId);
		params.add(clientId);
		List<ProjectResources> result = jdbcTemplate.query(SQL_SELECT_FILTER, params.toArray(), new ProjectResourcesMapper());
		return result;
	}
	
	@Override
	public List<ProjectResources> getByProjectIdAndResourceId(int projectId, int resourceId, int clientId) {
		Object[] primaryKey = new Object[] { projectId, resourceId, clientId };
		return jdbcTemplate.query(SELECT_BY_PROJECTID_RESOURCEID, primaryKey, new ProjectResourcesMapper());
	}

	@Override
	public List<ProjectResources> getAllProjectResources(int clientId) {
		List<ProjectResources> listProjectResources = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		try {
			listProjectResources = jdbcTemplate.query(SELECT_GET_ALL, params.toArray(), new ProjectResourcesMapper());
			return listProjectResources;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listProjectResources;
	}

	@Override
	public int update(ProjectResources projectResource) {

		int result = 0;
		try {
			String UPDATE_HR = "UPDATE project_resources "
					+ "SET quantity=?, salary =?  WHERE id = " + projectResource.getId();

			result = jdbcTemplate.update(UPDATE_HR,
					new Object[] {
							projectResource.getQuantity(),
							projectResource.getSalary()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}


	@Override
	public int delete(int projectResourceId) {
		int result = jdbcTemplate.update(DELETE_HR, new Object[] { projectResourceId });
		return result;
	}
}