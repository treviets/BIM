package com.redsun.service.service;

import com.redsun.service.entities.Versions;
import com.redsun.service.entities.Result;

/**
 * Versions Service interface
 */
public interface VersionsService {
	
    // Insert.
	Result insert(final Versions versions) ;

    // Update.
	Result update(final Versions versions) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listVersionsForPageAndFilter(final int itemsPerPage, final int pageNo, final Versions versions);

}
