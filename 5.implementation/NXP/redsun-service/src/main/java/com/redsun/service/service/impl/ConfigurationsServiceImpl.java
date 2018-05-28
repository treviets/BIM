package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ConfigurationsDao;
import com.redsun.service.entities.Configurations;
import com.redsun.service.entities.Result;
import com.redsun.service.service.ConfigurationsService;

/**
 * Configurations Service implementation
 */
@Service
public class ConfigurationsServiceImpl implements ConfigurationsService {

	@Autowired
	private ConfigurationsDao configurationsDao;

	// Insert.
	@Override
	public Result insert(final List<Configurations> listConfigurations) {
		final int data = configurationsDao.insert(listConfigurations);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("configurations", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result update(List<Configurations> listConfigurations) {
		final int data = configurationsDao.update(listConfigurations);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("configurations", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getByProject(int projectId, int clientId) {
		final List<Configurations> data = configurationsDao.getByProject(projectId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("configurations", data);
		// Return.
		return new Result(result, true);
	}
}
