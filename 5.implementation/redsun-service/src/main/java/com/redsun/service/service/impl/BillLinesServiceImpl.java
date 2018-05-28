package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.BillLinesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.BillLines;
import com.redsun.service.service.BillLinesService;

/**
 * BillLines Service implementation 
 */
@Service
public class BillLinesServiceImpl implements BillLinesService {
	
    @Autowired
    private BillLinesDao billLinesDao;
	
    // Insert.
	@Override
	public Result insert(final BillLines billLines) {
		// Call DAO.
		final int data = billLinesDao.insert(billLines);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final BillLines billLines) {
		// Call DAO.
		final int data = billLinesDao.update(billLines);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = billLinesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<BillLines> data = billLinesDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("billLines", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = billLinesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = billLinesDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listBillLinesForPageAndFilter(final int itemsPerPage, final int pageNo, final BillLines billLines) {
		// Call DAO.
		final List<BillLines> data = billLinesDao.listBillLinesForPageAndFilter(itemsPerPage, pageNo, billLines);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("billLines", data);
		// Return.
		return new Result(result, true);
	}
}
