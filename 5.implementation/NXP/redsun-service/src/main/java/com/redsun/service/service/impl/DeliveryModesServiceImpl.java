package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.DeliveryModesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.DeliveryModes;
import com.redsun.service.service.DeliveryModesService;

/**
 * DeliveryModes Service implementation 
 */
@Service
public class DeliveryModesServiceImpl implements DeliveryModesService {
	
    @Autowired
    private DeliveryModesDao deliveryModesDao;
	
    // Insert.
	@Override
	public Result insert(final DeliveryModes deliveryModes) {
		// Call DAO.
		final int data = deliveryModesDao.insert(deliveryModes);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final DeliveryModes deliveryModes) {
		// Call DAO.
		final int data = deliveryModesDao.update(deliveryModes);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = deliveryModesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<DeliveryModes> data = deliveryModesDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("deliveryModes", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = deliveryModesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = deliveryModesDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listDeliveryModesForPageAndFilter(final int itemsPerPage, final int pageNo, final DeliveryModes deliveryModes) {
		// Call DAO.
		final List<DeliveryModes> data = deliveryModesDao.listDeliveryModesForPageAndFilter(itemsPerPage, pageNo, deliveryModes);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("deliveryModes", data);
		// Return.
		return new Result(result, true);
	}
}
