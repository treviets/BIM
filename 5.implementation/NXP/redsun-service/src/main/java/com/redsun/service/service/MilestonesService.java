package com.redsun.service.service;

import com.redsun.service.entities.Milestones;
import com.redsun.service.entities.Result;

/**
 * Milestones Service interface
 */
public interface MilestonesService {
	
    // Insert.
	Result insert(final Milestones milestones) ;

    // Update.
	Result update(final Milestones milestones) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listMilestonesForPageAndFilter(final int itemsPerPage, final int pageNo, final Milestones milestones);
	
	// List by projectId.
	Result listMilestonesByProjectId(final int projectId);

}
