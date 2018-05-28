package com.redsun.service.service;

import com.redsun.service.entities.BillLines;
import com.redsun.service.entities.Result;

/**
 * BillLines Service interface
 */
public interface BillLinesService {
	
    // Insert.
	Result insert(final BillLines billLines) ;

    // Update.
	Result update(final BillLines billLines) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listBillLinesForPageAndFilter(final int itemsPerPage, final int pageNo, final BillLines billLines);

}
