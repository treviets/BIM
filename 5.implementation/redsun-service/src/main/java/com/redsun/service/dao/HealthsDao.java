package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Healths;

/**
 * Healths DAO interface
 */
public interface HealthsDao {
	
    // Insert.
	Integer insert(final Healths healths) ;

    // Update.
	int update(final Healths healths) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Healths> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Healths> listHealthsForPageAndFilter(final int itemsPerPage, final int pageNo, final Healths healths);

}
