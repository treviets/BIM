package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.VersionProjectsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.VersionProjects;
import com.redsun.service.service.VersionProjectsService;

/**
 * VersionProjects Service implementation 
 */
@Service
public class VersionProjectsServiceImpl implements VersionProjectsService {
	
    @Autowired
    private VersionProjectsDao versionProjectsDao;
	
    // Insert.
	@Override
	public Result insert(final VersionProjects versionProjects) {
		// Call DAO.
		final int data = versionProjectsDao.insert(versionProjects);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final VersionProjects versionProjects) {
		// Call DAO.
		final int data = versionProjectsDao.update(versionProjects);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = versionProjectsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<VersionProjects> data = versionProjectsDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("versionProjects", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = versionProjectsDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = versionProjectsDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listVersionProjectsForPageAndFilter(final int itemsPerPage, final int pageNo, final VersionProjects versionProjects) {
		// Call DAO.
		final List<VersionProjects> data = versionProjectsDao.listVersionProjectsForPageAndFilter(itemsPerPage, pageNo, versionProjects);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("versionProjects", data);
		// Return.
		return new Result(result, true);
	}
}
