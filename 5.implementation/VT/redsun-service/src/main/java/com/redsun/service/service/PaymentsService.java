package com.redsun.service.service;

import java.util.Map;

import com.redsun.service.entities.Payments;
import com.redsun.service.entities.Result;

/**
 * Payments Service interface
 */
public interface PaymentsService {
	
    // Insert.
	Result insert(final Payments payments) ;

    // Update.
	Result update(final Payments payments) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listPaymentsForPageAndFilter(final int itemsPerPage, final int pageNo, final Payments payments);

	// List extend for page and filter.
	Result listPaymentsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter);

}
