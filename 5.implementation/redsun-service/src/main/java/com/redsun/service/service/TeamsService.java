package com.redsun.service.service;

import com.redsun.service.entities.Teams;
import com.redsun.service.entities.Result;

/**
 * Teams Service interface
 */
public interface TeamsService {
	
    // Insert.
	Result insert(final Teams teams) ;

    // Update.
	Result update(final Teams teams) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listTeamsForPageAndFilter(final int itemsPerPage, final int pageNo, final Teams teams);

}
