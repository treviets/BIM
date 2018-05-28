package com.redsun.service.dao;

import com.redsun.service.entities.Tenders;

import java.util.List;
import java.util.Map;

/**
 * Tenders DAO interface
 */
public interface TendersDao {
	
    // Insert.
	Integer insert(final Tenders tenders) ;

    // Update.
	int update(final Tenders tenders) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Tenders> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	List<Map<Object, Object>> listTendersForPageAndFilter(final int itemsPerPage, final int pageNo, final Tenders tenders);

}
