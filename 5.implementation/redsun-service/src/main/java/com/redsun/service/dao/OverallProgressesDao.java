package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.OverallProgresses;

/**
 * OverallProgresses DAO interface
 */
public interface OverallProgressesDao {
	
    // Insert.
	Integer insert(final OverallProgresses overallProgresses) ;

    // Update.
	int update(final OverallProgresses overallProgresses) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<OverallProgresses> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<OverallProgresses> listOverallProgressesForPageAndFilter(final int itemsPerPage, final int pageNo, final OverallProgresses overallProgresses);

}
