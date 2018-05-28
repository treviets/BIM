package com.redsun.service.service;

import com.redsun.service.entities.CronExecutions;
import com.redsun.service.entities.Result;

/**
 * CronExecutions Service interface
 */
public interface CronExecutionsService {
	
    // Insert.
	Result insert(final CronExecutions cronExecutions) ;

    // Update.
	Result update(final CronExecutions cronExecutions) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listCronExecutionsForPageAndFilter(final int itemsPerPage, final int pageNo, final CronExecutions cronExecutions);

}
