package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.CronExecutionsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.CronExecutions;
import com.redsun.service.service.CronExecutionsService;

/**
 * CronExecutions Service implementation 
 */
@Service
public class CronExecutionsServiceImpl implements CronExecutionsService {
	
    @Autowired
    private CronExecutionsDao cronExecutionsDao;
	
    // Insert.
	@Override
	public Result insert(final CronExecutions cronExecutions) {
		// Call DAO.
		final int data = cronExecutionsDao.insert(cronExecutions);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final CronExecutions cronExecutions) {
		// Call DAO.
		final int data = cronExecutionsDao.update(cronExecutions);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = cronExecutionsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<CronExecutions> data = cronExecutionsDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("cronExecutions", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = cronExecutionsDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = cronExecutionsDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listCronExecutionsForPageAndFilter(final int itemsPerPage, final int pageNo, final CronExecutions cronExecutions) {
		// Call DAO.
		final List<CronExecutions> data = cronExecutionsDao.listCronExecutionsForPageAndFilter(itemsPerPage, pageNo, cronExecutions);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("cronExecutions", data);
		// Return.
		return new Result(result, true);
	}
}
