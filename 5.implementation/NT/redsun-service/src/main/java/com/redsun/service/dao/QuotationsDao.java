package com.redsun.service.dao;

import java.util.List;
import java.util.Map;

import com.redsun.service.entities.Quotations;

/**
 * Quotations DAO interface
 */
public interface QuotationsDao {
	
    // Insert.
	Integer insert(final Quotations quotations) ;

    // Update.
	int update(final Quotations quotations) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Quotations> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Quotations> listQuotationsForPageAndFilter(final int itemsPerPage, final int pageNo, final Quotations quotations);

	// List extend for page and filter.
	List<Map<Object, Object>> listQuotationsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter);

}
