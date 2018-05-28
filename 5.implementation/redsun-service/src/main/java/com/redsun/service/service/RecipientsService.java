package com.redsun.service.service;

import com.redsun.service.entities.Recipients;
import com.redsun.service.entities.Result;

/**
 * Recipients Service interface
 */
public interface RecipientsService {
	
    // Insert.
	Result insert(final Recipients recipients) ;

    // Update.
	Result update(final Recipients recipients) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listRecipientsForPageAndFilter(final int itemsPerPage, final int pageNo, final Recipients recipients);

}
