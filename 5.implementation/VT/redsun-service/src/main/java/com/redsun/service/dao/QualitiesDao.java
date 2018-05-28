package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Qualities;

/**
 * Qualities DAO interface
 */
public interface QualitiesDao {
	
    // Insert.
	Integer insert(final Qualities qualities) ;

    // Update.
	int update(final Qualities qualities) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Qualities> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Qualities> listQualitiesForPageAndFilter(final int itemsPerPage, final int pageNo, final Qualities qualities);

}
