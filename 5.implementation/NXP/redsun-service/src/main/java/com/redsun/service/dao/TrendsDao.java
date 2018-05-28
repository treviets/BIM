package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Trends;

/**
 * Trends DAO interface
 */
public interface TrendsDao {
	
    // Insert.
	Integer insert(final Trends trends) ;

    // Update.
	int update(final Trends trends) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Trends> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Trends> listTrendsForPageAndFilter(final int itemsPerPage, final int pageNo, final Trends trends);

}
