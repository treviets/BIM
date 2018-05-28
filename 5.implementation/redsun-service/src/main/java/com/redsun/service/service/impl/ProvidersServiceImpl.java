package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ProvidersDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Providers;
import com.redsun.service.service.ProvidersService;

/**
 * Providers Service implementation 
 */
@Service
public class ProvidersServiceImpl implements ProvidersService {
	
    @Autowired
    private ProvidersDao providersDao;
	
    // Insert.
	@Override
	public Result insert(final Providers providers) {
		// Call DAO.
		final int data = providersDao.insert(providers);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Providers providers) {
		// Call DAO.
		final int data = providersDao.update(providers);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = providersDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Providers> data = providersDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("providers", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = providersDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = providersDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listProvidersForPageAndFilter(final int itemsPerPage, final int pageNo, final Providers providers) {
		// Call DAO.
		final List<Providers> data = providersDao.listProvidersForPageAndFilter(itemsPerPage, pageNo, providers);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("providers", data);
		// Return.
		return new Result(result, true);
	}
}
