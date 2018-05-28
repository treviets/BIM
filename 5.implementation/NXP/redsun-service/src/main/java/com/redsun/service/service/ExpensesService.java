package com.redsun.service.service;

import com.redsun.service.entities.Expenses;
import com.redsun.service.entities.Result;

import java.io.IOException;

/**
 * Expenses Service interface
 */
public interface ExpensesService {
	
	Result insert(final Expenses expenses) ;
	Result update(final Expenses expenses) ;
	Result delete(final String scope, final Integer id) throws IOException;
	Result getById(final Integer id) ;
	Result exists(final Integer id) ;
	Result count() ;
	Result listExpensesForPageAndFilter(final Integer clientId, final String scope, final Integer pageNo, final Integer itemsPerPage);

}
