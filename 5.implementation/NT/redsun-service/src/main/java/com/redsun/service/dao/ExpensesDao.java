package com.redsun.service.dao;

import java.util.List;
import java.util.Map;

import com.redsun.service.entities.Expenses;

/**
 * Expenses DAO interface
 */
public interface ExpensesDao {
	
	Integer insert(final Expenses expenses) ;
	Integer update(final Expenses expenses) ;
	Integer delete(final Integer id) ;
	List<Expenses> getById(final Integer id) ;
	boolean exists(final Integer id) ;
	Long count() ;
	List<Map<Object, Object>> getExpensesByType(final Integer clientId, final String scope, final Integer pageNo, final Integer itemsPerPage);

}
