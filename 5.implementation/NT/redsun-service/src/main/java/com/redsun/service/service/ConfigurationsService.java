package com.redsun.service.service;

import java.util.List;

import com.redsun.service.entities.Configurations;
import com.redsun.service.entities.Result;

/**
 * Configuration Service interface
 */
public interface ConfigurationsService {

	// Insert.
	Result insert(final List<Configurations> listConfigurations);

	//get by project
	Result getByProject(int projectId, int clientId);
	
	// update.
	Result update(final List<Configurations> listConfigurations);
}
