package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Versions;

/**
 * Versions DAO interface
 */
public interface VersionsDao {
	
    // Insert.
	Integer insert(final Versions versions) ;

    // Update.
	int update(final Versions versions) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Versions> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Versions> listVersionsForPageAndFilter(final int itemsPerPage, final int pageNo, final Versions versions);

}
