package com.redsun.service.service;

import com.redsun.service.entities.DeliveryModes;
import com.redsun.service.entities.Result;

/**
 * DeliveryModes Service interface
 */
public interface DeliveryModesService {
	
    // Insert.
	Result insert(final DeliveryModes deliveryModes) ;

    // Update.
	Result update(final DeliveryModes deliveryModes) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listDeliveryModesForPageAndFilter(final int itemsPerPage, final int pageNo, final DeliveryModes deliveryModes);

}
