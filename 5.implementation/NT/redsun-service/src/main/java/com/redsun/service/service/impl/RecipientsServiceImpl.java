package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.RecipientsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Recipients;
import com.redsun.service.service.RecipientsService;

/**
 * Recipients Service implementation 
 */
@Service
public class RecipientsServiceImpl implements RecipientsService {
	
    @Autowired
    private RecipientsDao recipientsDao;
	
    // Insert.
	@Override
	public Result insert(final Recipients recipients) {
		// Call DAO.
		final int data = recipientsDao.insert(recipients);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Recipients recipients) {
		// Call DAO.
		final int data = recipientsDao.update(recipients);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = recipientsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Recipients> data = recipientsDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("recipients", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = recipientsDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = recipientsDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listRecipientsForPageAndFilter(final int itemsPerPage, final int pageNo, final Recipients recipients) {
		// Call DAO.
		final List<Recipients> data = recipientsDao.listRecipientsForPageAndFilter(itemsPerPage, pageNo, recipients);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("recipients", data);
		// Return.
		return new Result(result, true);
	}
}
