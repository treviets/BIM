package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.ConfigurationsDao;
import com.redsun.service.dao.mapper.ConfigurationsMapper;
import com.redsun.service.entities.Configurations;

/**
 * Configurations DAO implementation
 */
@Repository
public class ConfigurationsDaoImpl implements ConfigurationsDao {
	private static Logger log = Logger.getLogger(ConfigurationsDaoImpl .class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static String ADD_CONFIGURATION = "insert into configurations (id_project, level, factor, client_id)  VALUES (?, ?, ?, ?)  ";
	private static String SELECT_GET_BY_PROJECT = "SELECT c.id, c.id_project, c.level, c.factor, c.client_id,"
			+ "roles.name as role_name FROM configurations as c "
			+ "INNER JOIN roles ON c.level = roles.id WHERE c.id_project = ? AND c.client_id = ?";
	private static String UPDATE_CONFIGURATION = "UPDATE configurations SET level=?, factor=? WHERE id_project = ? AND client_id = ?";

	@Override
	public int insert(List<Configurations> listConfigurations) {
		int result = 0;
		for (Configurations configurations : listConfigurations) {

			result = jdbcTemplate.update(ADD_CONFIGURATION, new Object[] {
					configurations.getProjectId(),
					configurations.getLevel(), 
					configurations.getFactor(),
					configurations.getClientId() });
		}
		return result;
	}

	@Override
	public int update(List<Configurations> listConfigurations) {
		int result = 0;
		try {
		for (Configurations configurations : listConfigurations) {

			result = jdbcTemplate.update(UPDATE_CONFIGURATION, new Object[] {
					configurations.getLevel(), 
					configurations.getFactor(),
					configurations.getProjectId(),
					configurations.getClientId()
					});
		}
		return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Configurations> getByProject(int projectId, int clientId) {
		List<Configurations> listConfigurations = null;
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);

		try {
			listConfigurations = jdbcTemplate.query(SELECT_GET_BY_PROJECT, params.toArray(), new ConfigurationsMapper());
			return listConfigurations;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listConfigurations;
	}


}
