package com.redsun.service.service;

import com.redsun.service.entities.Qualities;
import com.redsun.service.entities.Result;

/**
 * Qualities Service interface
 */
public interface QualitiesService {
	
    // Insert.
	Result insert(final Qualities qualities) ;

    // Update.
	Result update(final Qualities qualities) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listQualitiesForPageAndFilter(final int itemsPerPage, final int pageNo, final Qualities qualities);

}
