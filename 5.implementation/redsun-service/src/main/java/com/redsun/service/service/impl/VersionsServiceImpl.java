package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.VersionsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Versions;
import com.redsun.service.service.VersionsService;

/**
 * Versions Service implementation 
 */
@Service
public class VersionsServiceImpl implements VersionsService {
	
    @Autowired
    private VersionsDao versionsDao;
	
    // Insert.
	@Override
	public Result insert(final Versions versions) {
		// Call DAO.
		final int data = versionsDao.insert(versions);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Versions versions) {
		// Call DAO.
		final int data = versionsDao.update(versions);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = versionsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Versions> data = versionsDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("versions", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = versionsDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = versionsDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listVersionsForPageAndFilter(final int itemsPerPage, final int pageNo, final Versions versions) {
		// Call DAO.
		final List<Versions> data = versionsDao.listVersionsForPageAndFilter(itemsPerPage, pageNo, versions);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("versions", data);
		// Return.
		return new Result(result, true);
	}
}
