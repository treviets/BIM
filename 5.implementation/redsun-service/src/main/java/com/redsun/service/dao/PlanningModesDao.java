package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.PlanningModes;

/**
 * PlanningModes DAO interface
 */
public interface PlanningModesDao {
	
    // Insert.
	Integer insert(final PlanningModes planningModes) ;

    // Update.
	int update(final PlanningModes planningModes) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<PlanningModes> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<PlanningModes> listPlanningModesForPageAndFilter(final int itemsPerPage, final int pageNo, final PlanningModes planningModes);

}
