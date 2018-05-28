package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ActivityPricesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.ActivityPrices;
import com.redsun.service.service.ActivityPricesService;

/**
 * ActivityPrices Service implementation 
 */
@Service
public class ActivityPricesServiceImpl implements ActivityPricesService {
	
    @Autowired
    private ActivityPricesDao activityPricesDao;
	
    // Insert.
	@Override
	public Result insert(final ActivityPrices activityPrices) {
		// Call DAO.
		final int data = activityPricesDao.insert(activityPrices);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final ActivityPrices activityPrices) {
		// Call DAO.
		final int data = activityPricesDao.update(activityPrices);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = activityPricesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<ActivityPrices> data = activityPricesDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("activityPrices", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = activityPricesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = activityPricesDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listActivityPricesForPageAndFilter(final int itemsPerPage, final int pageNo, final ActivityPrices activityPrices) {
		// Call DAO.
		final List<ActivityPrices> data = activityPricesDao.listActivityPricesForPageAndFilter(itemsPerPage, pageNo, activityPrices);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("activityPrices", data);
		// Return.
		return new Result(result, true);
	}
}
