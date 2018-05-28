package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ExpenseDetailTypesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.ExpenseDetailTypes;
import com.redsun.service.service.ExpenseDetailTypesService;

/**
 * ExpenseDetailTypes Service implementation 
 */
@Service
public class ExpenseDetailTypesServiceImpl implements ExpenseDetailTypesService {
	
    @Autowired
    private ExpenseDetailTypesDao expenseDetailTypesDao;
	
    // Insert.
	@Override
	public Result insert(final ExpenseDetailTypes expenseDetailTypes) {
		// Call DAO.
		final int data = expenseDetailTypesDao.insert(expenseDetailTypes);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final ExpenseDetailTypes expenseDetailTypes) {
		// Call DAO.
		final int data = expenseDetailTypesDao.update(expenseDetailTypes);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = expenseDetailTypesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<ExpenseDetailTypes> data = expenseDetailTypesDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("expenseDetailTypes", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = expenseDetailTypesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = expenseDetailTypesDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listExpenseDetailTypesForPageAndFilter(final int itemsPerPage, final int pageNo, final ExpenseDetailTypes expenseDetailTypes) {
		// Call DAO.
		final List<ExpenseDetailTypes> data = expenseDetailTypesDao.listExpenseDetailTypesForPageAndFilter(itemsPerPage, pageNo, expenseDetailTypes);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("expenseDetailTypes", data);
		// Return.
		return new Result(result, true);
	}
}
