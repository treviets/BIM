package com.redsun.service.dao;

import java.util.List;
import java.util.Map;

import com.redsun.service.entities.ExpenseDetails;

/**
 * ExpenseDetails DAO interface
 */
public interface ExpenseDetailsDao {
	
    // Insert.
	Integer insert(final ExpenseDetails expenseDetails) ;

    // Update.
	int update(final ExpenseDetails expenseDetails) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<ExpenseDetails> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Map<Object, Object>> listExpenseDetailsByExpensesId(final Integer clientId, final Integer expensesId, final Integer itemsPerPage, final Integer pageNo);

}
