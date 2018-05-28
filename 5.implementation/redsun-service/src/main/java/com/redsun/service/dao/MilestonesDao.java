package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Milestones;

/**
 * Milestones DAO interface
 */
public interface MilestonesDao {
	
    // Insert.
	Integer insert(final Milestones milestones) ;

    // Update.
	int update(final Milestones milestones) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Milestones> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Milestones> listMilestonesForPageAndFilter(final int itemsPerPage, final int pageNo, final Milestones milestones);
	// List by projectId.
	List<Milestones> listMilestonesByProjectId(final int projectId);

}
