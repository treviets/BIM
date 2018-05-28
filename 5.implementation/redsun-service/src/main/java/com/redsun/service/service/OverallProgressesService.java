package com.redsun.service.service;

import com.redsun.service.entities.OverallProgresses;
import com.redsun.service.entities.Result;

/**
 * OverallProgresses Service interface
 */
public interface OverallProgressesService {
	
    // Insert.
	Result insert(final OverallProgresses overallProgresses) ;

    // Update.
	Result update(final OverallProgresses overallProgresses) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listOverallProgressesForPageAndFilter(final int itemsPerPage, final int pageNo, final OverallProgresses overallProgresses);

}
