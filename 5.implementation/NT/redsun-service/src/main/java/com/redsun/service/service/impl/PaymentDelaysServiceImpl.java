package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.PaymentDelaysDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.PaymentDelays;
import com.redsun.service.service.PaymentDelaysService;

/**
 * PaymentDelays Service implementation 
 */
@Service
public class PaymentDelaysServiceImpl implements PaymentDelaysService {
	
    @Autowired
    private PaymentDelaysDao paymentDelaysDao;
	
    // Insert.
	@Override
	public Result insert(final PaymentDelays paymentDelays) {
		// Call DAO.
		final int data = paymentDelaysDao.insert(paymentDelays);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final PaymentDelays paymentDelays) {
		// Call DAO.
		final int data = paymentDelaysDao.update(paymentDelays);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = paymentDelaysDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<PaymentDelays> data = paymentDelaysDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("paymentDelays", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = paymentDelaysDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = paymentDelaysDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listPaymentDelaysForPageAndFilter(final int itemsPerPage, final int pageNo, final PaymentDelays paymentDelays) {
		// Call DAO.
		final List<PaymentDelays> data = paymentDelaysDao.listPaymentDelaysForPageAndFilter(itemsPerPage, pageNo, paymentDelays);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("paymentDelays", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listAll(int clientId) {
		// Call DAO.
				final List<PaymentDelays> data = paymentDelaysDao.listAll(clientId);
				final Map<String, Object> result = new HashMap<String, Object>();
				result.put("paymentDelays", data);
				// Return.
				return new Result(result, true);
	}
}
