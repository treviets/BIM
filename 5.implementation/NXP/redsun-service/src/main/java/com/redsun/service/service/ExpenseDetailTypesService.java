package com.redsun.service.service;

import com.redsun.service.entities.ExpenseDetailTypes;
import com.redsun.service.entities.Result;

/**
 * ExpenseDetailTypes Service interface
 */
public interface ExpenseDetailTypesService {
	
    // Insert.
	Result insert(final ExpenseDetailTypes expenseDetailTypes) ;

    // Update.
	Result update(final ExpenseDetailTypes expenseDetailTypes) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listExpenseDetailTypesForPageAndFilter(final int itemsPerPage, final int pageNo, final ExpenseDetailTypes expenseDetailTypes);

}
