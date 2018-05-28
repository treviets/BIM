package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Referencables;

/**
 * Referencables DAO interface
 */
public interface ReferencablesDao {
	
    // Insert.
	Integer insert(final Referencables referencables) ;

    // Update.
	int update(final Referencables referencables) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Referencables> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Referencables> listReferencablesForPageAndFilter(final int itemsPerPage, final int pageNo, final Referencables referencables);

}
