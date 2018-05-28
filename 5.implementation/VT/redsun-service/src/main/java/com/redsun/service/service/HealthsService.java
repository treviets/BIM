package com.redsun.service.service;

import com.redsun.service.entities.Healths;
import com.redsun.service.entities.Result;

/**
 * Healths Service interface
 */
public interface HealthsService {
	
    // Insert.
	Result insert(final Healths healths) ;

    // Update.
	Result update(final Healths healths) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listHealthsForPageAndFilter(final int itemsPerPage, final int pageNo, final Healths healths);

}
