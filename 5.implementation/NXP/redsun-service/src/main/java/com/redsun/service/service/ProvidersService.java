package com.redsun.service.service;

import com.redsun.service.entities.Providers;
import com.redsun.service.entities.Result;

/**
 * Providers Service interface
 */
public interface ProvidersService {
	
    // Insert.
	Result insert(final Providers providers) ;

    // Update.
	Result update(final Providers providers) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listProvidersForPageAndFilter(final int itemsPerPage, final int pageNo, final Providers providers);

}
