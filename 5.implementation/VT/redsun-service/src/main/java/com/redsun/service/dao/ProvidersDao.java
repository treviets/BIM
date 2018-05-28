package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Providers;

/**
 * Providers DAO interface
 */
public interface ProvidersDao {
	
    // Insert.
	Integer insert(final Providers providers) ;

    // Update.
	int update(final Providers providers) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Providers> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Providers> listProvidersForPageAndFilter(final int itemsPerPage, final int pageNo, final Providers providers);

}
