package com.redsun.service.service;

import com.redsun.service.entities.VersionProjects;
import com.redsun.service.entities.Result;

/**
 * VersionProjects Service interface
 */
public interface VersionProjectsService {
	
    // Insert.
	Result insert(final VersionProjects versionProjects) ;

    // Update.
	Result update(final VersionProjects versionProjects) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listVersionProjectsForPageAndFilter(final int itemsPerPage, final int pageNo, final VersionProjects versionProjects);

}
