package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.ExpenseDetailTypes;

/**
 * ExpenseDetailTypes DAO interface
 */
public interface ExpenseDetailTypesDao {
	
    // Insert.
	Integer insert(final ExpenseDetailTypes expenseDetailTypes) ;

    // Update.
	int update(final ExpenseDetailTypes expenseDetailTypes) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<ExpenseDetailTypes> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<ExpenseDetailTypes> listExpenseDetailTypesForPageAndFilter(final int itemsPerPage, final int pageNo, final ExpenseDetailTypes expenseDetailTypes);

}
