package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Teams;

/**
 * Teams DAO interface
 */
public interface TeamsDao {
	
    // Insert.
	Integer insert(final Teams teams) ;

    // Update.
	int update(final Teams teams) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Teams> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Teams> listTeamsForPageAndFilter(final int itemsPerPage, final int pageNo, final Teams teams);

}
