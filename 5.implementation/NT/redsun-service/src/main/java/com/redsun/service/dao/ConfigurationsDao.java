package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Configurations;

/**
 * Configurations DAO interface
 */
public interface ConfigurationsDao {

	// insert.
	int insert(final List<Configurations> listConfigurations);

	//get by project
	List<Configurations>  getByProject(int projectId, int clientId);
	
	//update
	int update(List<Configurations> listConfigurations);
}
