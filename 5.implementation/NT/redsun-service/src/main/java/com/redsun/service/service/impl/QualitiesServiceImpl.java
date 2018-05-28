package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.QualitiesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Qualities;
import com.redsun.service.service.QualitiesService;

/**
 * Qualities Service implementation 
 */
@Service
public class QualitiesServiceImpl implements QualitiesService {
	
    @Autowired
    private QualitiesDao qualitiesDao;
	
    // Insert.
	@Override
	public Result insert(final Qualities qualities) {
		// Call DAO.
		final int data = qualitiesDao.insert(qualities);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Qualities qualities) {
		// Call DAO.
		final int data = qualitiesDao.update(qualities);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = qualitiesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Qualities> data = qualitiesDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("qualities", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = qualitiesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = qualitiesDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listQualitiesForPageAndFilter(final int itemsPerPage, final int pageNo, final Qualities qualities) {
		// Call DAO.
		final List<Qualities> data = qualitiesDao.listQualitiesForPageAndFilter(itemsPerPage, pageNo, qualities);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("qualities", data);
		// Return.
		return new Result(result, true);
	}
}
