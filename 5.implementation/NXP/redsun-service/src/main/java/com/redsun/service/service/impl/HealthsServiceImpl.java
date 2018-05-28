package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.HealthsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Healths;
import com.redsun.service.service.HealthsService;

/**
 * Healths Service implementation 
 */
@Service
public class HealthsServiceImpl implements HealthsService {
	
    @Autowired
    private HealthsDao healthsDao;
	
    // Insert.
	@Override
	public Result insert(final Healths healths) {
		// Call DAO.
		final int data = healthsDao.insert(healths);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Healths healths) {
		// Call DAO.
		final int data = healthsDao.update(healths);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = healthsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Healths> data = healthsDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("healths", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = healthsDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = healthsDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listHealthsForPageAndFilter(final int itemsPerPage, final int pageNo, final Healths healths) {
		// Call DAO.
		final List<Healths> data = healthsDao.listHealthsForPageAndFilter(itemsPerPage, pageNo, healths);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("healths", data);
		// Return.
		return new Result(result, true);
	}
}
