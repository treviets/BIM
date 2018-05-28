package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.OverallProgressesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.OverallProgresses;
import com.redsun.service.service.OverallProgressesService;

/**
 * OverallProgresses Service implementation 
 */
@Service
public class OverallProgressesServiceImpl implements OverallProgressesService {
	
    @Autowired
    private OverallProgressesDao overallProgressesDao;
	
    // Insert.
	@Override
	public Result insert(final OverallProgresses overallProgresses) {
		// Call DAO.
		final int data = overallProgressesDao.insert(overallProgresses);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final OverallProgresses overallProgresses) {
		// Call DAO.
		final int data = overallProgressesDao.update(overallProgresses);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = overallProgressesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<OverallProgresses> data = overallProgressesDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("overallProgresses", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = overallProgressesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = overallProgressesDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listOverallProgressesForPageAndFilter(final int itemsPerPage, final int pageNo, final OverallProgresses overallProgresses) {
		// Call DAO.
		final List<OverallProgresses> data = overallProgressesDao.listOverallProgressesForPageAndFilter(itemsPerPage, pageNo, overallProgresses);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("overallProgresses", data);
		// Return.
		return new Result(result, true);
	}
}
