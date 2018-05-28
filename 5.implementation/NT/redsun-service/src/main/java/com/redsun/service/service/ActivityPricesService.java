package com.redsun.service.service;

import com.redsun.service.entities.ActivityPrices;
import com.redsun.service.entities.Result;

/**
 * ActivityPrices Service interface
 */
public interface ActivityPricesService {
	
    // Insert.
	Result insert(final ActivityPrices activityPrices) ;

    // Update.
	Result update(final ActivityPrices activityPrices) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listActivityPricesForPageAndFilter(final int itemsPerPage, final int pageNo, final ActivityPrices activityPrices);

}
