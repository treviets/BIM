package com.redsun.service.service;

import com.redsun.service.entities.Trends;
import com.redsun.service.entities.Result;

/**
 * Trends Service interface
 */
public interface TrendsService {
	
    // Insert.
	Result insert(final Trends trends) ;

    // Update.
	Result update(final Trends trends) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listTrendsForPageAndFilter(final int itemsPerPage, final int pageNo, final Trends trends);

}
