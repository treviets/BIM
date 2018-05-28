package com.redsun.service.service;

import com.redsun.service.entities.Profiles;
import com.redsun.service.entities.Result;

/**
 * Profiles Service interface
 */
public interface ProfilesService {
	
    // Insert.
	Result insert(final Profiles profiles) ;

    // Update.
	Result update(final Profiles profiles) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listProfilesForPageAndFilter(final int itemsPerPage, final int pageNo, final Profiles profiles);

}
