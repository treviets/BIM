package com.redsun.service.service;

import com.redsun.service.entities.PlanningModes;
import com.redsun.service.entities.Result;

/**
 * PlanningModes Service interface
 */
public interface PlanningModesService {
	
    // Insert.
	Result insert(final PlanningModes planningModes) ;

    // Update.
	Result update(final PlanningModes planningModes) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listPlanningModesForPageAndFilter(final int itemsPerPage, final int pageNo, final PlanningModes planningModes);

}
