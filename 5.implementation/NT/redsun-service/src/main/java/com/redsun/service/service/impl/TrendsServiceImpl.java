package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.TrendsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Trends;
import com.redsun.service.service.TrendsService;

/**
 * Trends Service implementation 
 */
@Service
public class TrendsServiceImpl implements TrendsService {
	
    @Autowired
    private TrendsDao trendsDao;
	
    // Insert.
	@Override
	public Result insert(final Trends trends) {
		// Call DAO.
		final int data = trendsDao.insert(trends);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Trends trends) {
		// Call DAO.
		final int data = trendsDao.update(trends);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = trendsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Trends> data = trendsDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("trends", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = trendsDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = trendsDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listTrendsForPageAndFilter(final int itemsPerPage, final int pageNo, final Trends trends) {
		// Call DAO.
		final List<Trends> data = trendsDao.listTrendsForPageAndFilter(itemsPerPage, pageNo, trends);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("trends", data);
		// Return.
		return new Result(result, true);
	}
}
