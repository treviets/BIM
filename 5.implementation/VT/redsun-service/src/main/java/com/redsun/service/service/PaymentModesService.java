package com.redsun.service.service;

import com.redsun.service.entities.PaymentModes;
import com.redsun.service.entities.Result;

/**
 * PaymentModes Service interface
 */
public interface PaymentModesService {
	
    // Insert.
	Result insert(final PaymentModes paymentModes) ;

    // Update.
	Result update(final PaymentModes paymentModes) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listPaymentModesForPageAndFilter(final int itemsPerPage, final int pageNo, final PaymentModes paymentModes);

}
