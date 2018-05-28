package com.redsun.service.service.impl;

import com.redsun.service.dao.ExpenseDetailsDao;
import com.redsun.service.entities.ExpenseDetails;
import com.redsun.service.entities.Result;
import com.redsun.service.service.ExpenseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ExpenseDetails Service implementation 
 */
@Service
public class ExpenseDetailsServiceImpl implements ExpenseDetailsService {
	
    @Autowired
    private ExpenseDetailsDao expenseDetailsDao;
	
    // Insert.
	@Override
	public Result insert(final ExpenseDetails expenseDetails) {
		// Call DAO.
		final int data = expenseDetailsDao.insert(expenseDetails);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final ExpenseDetails expenseDetails) {
		// Call DAO.
		final int data = expenseDetailsDao.update(expenseDetails);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = expenseDetailsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<ExpenseDetails> data = expenseDetailsDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("expenseDetails", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = expenseDetailsDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = expenseDetailsDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listExpenseDetailsByExpensesId(Integer clientId, Integer expensesId, Integer itemsPerPage, Integer pageNo) {
		final List<Map<Object, Object>> data = expenseDetailsDao.listExpenseDetailsByExpensesId(clientId, expensesId, itemsPerPage, pageNo);
		final Map<String, Object> result = new HashMap<>();
		result.put("expenseDetails", data);
		return new Result(result, true);
	}
}
