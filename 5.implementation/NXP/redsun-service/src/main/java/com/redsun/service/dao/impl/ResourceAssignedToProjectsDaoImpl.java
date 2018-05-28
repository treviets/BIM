package com.redsun.service.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.ResourceAssignedToProjectsDao;
import com.redsun.service.dao.mapper.ResourceAssignedToProjectsMapper;
import com.redsun.service.entities.ResourceAssignedToProjects;

@Repository
public class ResourceAssignedToProjectsDaoImpl implements ResourceAssignedToProjectsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	static private String SELECT_RESOURCE_BY_PROJECT = "select id, project_id, resource_id, team_id from resource_assigned_to_project where project_id = ?";
	private static Logger log = Logger.getLogger(ResourceAssignedToProjectsDaoImpl.class);

	public List<ResourceAssignedToProjects> getResourceByProject(int projectId) {
		// TODO Auto-generated method stub
		List<ResourceAssignedToProjects> listRAPs = null;
		try {
			listRAPs = jdbcTemplate.query(SELECT_RESOURCE_BY_PROJECT, new Object[] { projectId }, new ResourceAssignedToProjectsMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listRAPs;
	}
}
