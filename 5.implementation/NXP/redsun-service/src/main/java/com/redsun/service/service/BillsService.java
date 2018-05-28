package com.redsun.service.service;

import java.util.Map;

import com.redsun.service.entities.Bills;
import com.redsun.service.entities.Result;

/**
 * Bills Service interface
 */
public interface BillsService {
	
    // Insert.
	Result insert(final Bills bills) ;

    // Update.
	Result update(final Bills bills) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listBillsForPageAndFilter(final int itemsPerPage, final int pageNo, final Bills bills);

	// List extend for page and filter.
	Result listBillsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter);

}
