package com.redsun.service.service;

import com.redsun.service.entities.Referencables;
import com.redsun.service.entities.Result;

/**
 * Referencables Service interface
 */
public interface ReferencablesService {
	
    // Insert.
	Result insert(final Referencables referencables) ;

    // Update.
	Result update(final Referencables referencables) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listReferencablesForPageAndFilter(final int itemsPerPage, final int pageNo, final Referencables referencables);

}
