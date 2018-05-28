package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.StatusesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Statuses;
import com.redsun.service.service.StatusesService;

/**
 * Statuses Service implementation 
 */
@Service
public class StatusesServiceImpl implements StatusesService {
	
    @Autowired
    private StatusesDao statusesDao;
	
    // Insert.
	@Override
	public Result insert(final Statuses statuses) {
		// Call DAO.
		final int data = statusesDao.insert(statuses);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Statuses statuses) {
		// Call DAO.
		final int data = statusesDao.update(statuses);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = statusesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Statuses> data = statusesDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("statuses", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = statusesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = statusesDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listStatusesForPageAndFilter(final int itemsPerPage, final int pageNo, final Statuses statuses) {
		// Call DAO.
		final List<Statuses> data = statusesDao.listStatusesForPageAndFilter(itemsPerPage, pageNo, statuses);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("statuses", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listAllStatuses(int clientId) {
		// Call DAO.
				final List<Statuses> data = statusesDao.listAllStatuses(clientId);
				final Map<String, Object> result = new HashMap<String, Object>();
				result.put("statuses", data);
				// Return.
				return new Result(result, true);
			}

	@Override
	public Result getByScope(int clientId, String scope) {
		// Call DAO.
		final List<Statuses> data = statusesDao.getByScope(clientId, scope);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("statuses", data);
		// Return.
		return new Result(result, true);
	}
}
