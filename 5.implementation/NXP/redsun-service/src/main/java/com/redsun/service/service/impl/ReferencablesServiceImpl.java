package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ReferencablesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Referencables;
import com.redsun.service.service.ReferencablesService;

/**
 * Referencables Service implementation 
 */
@Service
public class ReferencablesServiceImpl implements ReferencablesService {
	
    @Autowired
    private ReferencablesDao referencablesDao;
	
    // Insert.
	@Override
	public Result insert(final Referencables referencables) {
		// Call DAO.
		final int data = referencablesDao.insert(referencables);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Referencables referencables) {
		// Call DAO.
		final int data = referencablesDao.update(referencables);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = referencablesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Referencables> data = referencablesDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("referencables", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = referencablesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = referencablesDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listReferencablesForPageAndFilter(final int itemsPerPage, final int pageNo, final Referencables referencables) {
		// Call DAO.
		final List<Referencables> data = referencablesDao.listReferencablesForPageAndFilter(itemsPerPage, pageNo, referencables);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("referencables", data);
		// Return.
		return new Result(result, true);
	}
}
