package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.CronExecutions;

/**
 * CronExecutions DAO interface
 */
public interface CronExecutionsDao {
	
    // Insert.
	Integer insert(final CronExecutions cronExecutions) ;

    // Update.
	int update(final CronExecutions cronExecutions) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<CronExecutions> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<CronExecutions> listCronExecutionsForPageAndFilter(final int itemsPerPage, final int pageNo, final CronExecutions cronExecutions);

}
