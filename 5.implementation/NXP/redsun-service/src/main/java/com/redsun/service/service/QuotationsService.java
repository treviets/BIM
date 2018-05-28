package com.redsun.service.service;

import java.util.Map;

import com.redsun.service.entities.Quotations;
import com.redsun.service.entities.Result;

/**
 * Quotations Service interface
 */
public interface QuotationsService {
	
    // Insert.
	Result insert(final Quotations quotations) ;

    // Update.
	Result update(final Quotations quotations) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listQuotationsForPageAndFilter(final int itemsPerPage, final int pageNo, final Quotations quotations);

	// List extend for page and filter.
	Result listQuotationsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter);

}
