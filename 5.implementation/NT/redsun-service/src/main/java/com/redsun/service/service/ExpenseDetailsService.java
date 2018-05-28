package com.redsun.service.service;

import com.redsun.service.entities.ExpenseDetails;
import com.redsun.service.entities.Result;

/**
 * ExpenseDetails Service interface
 */
public interface ExpenseDetailsService {
	
    // Insert.
	Result insert(final ExpenseDetails expenseDetails) ;

    // Update.
	Result update(final ExpenseDetails expenseDetails) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listExpenseDetailsByExpensesId(final Integer clientId, final Integer expensesId, final Integer itemsPerPage, final Integer pageNo);

}
