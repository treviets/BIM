package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.PaymentModesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.PaymentModes;
import com.redsun.service.service.PaymentModesService;

/**
 * PaymentModes Service implementation 
 */
@Service
public class PaymentModesServiceImpl implements PaymentModesService {
	
    @Autowired
    private PaymentModesDao paymentModesDao;
	
    // Insert.
	@Override
	public Result insert(final PaymentModes paymentModes) {
		// Call DAO.
		final int data = paymentModesDao.insert(paymentModes);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final PaymentModes paymentModes) {
		// Call DAO.
		final int data = paymentModesDao.update(paymentModes);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = paymentModesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<PaymentModes> data = paymentModesDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("paymentModes", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = paymentModesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = paymentModesDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listPaymentModesForPageAndFilter(final int itemsPerPage, final int pageNo, final PaymentModes paymentModes) {
		// Call DAO.
		final List<PaymentModes> data = paymentModesDao.listPaymentModesForPageAndFilter(itemsPerPage, pageNo, paymentModes);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("paymentModes", data);
		// Return.
		return new Result(result, true);
	}
}
