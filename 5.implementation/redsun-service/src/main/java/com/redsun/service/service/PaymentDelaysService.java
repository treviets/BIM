package com.redsun.service.service;

import com.redsun.service.entities.PaymentDelays;
import com.redsun.service.entities.Result;

/**
 * PaymentDelays Service interface
 */
public interface PaymentDelaysService {

	// Insert.
	Result insert(final PaymentDelays paymentDelays);

	// Update.
	Result update(final PaymentDelays paymentDelays);

	// Delete.
	Result delete(final Integer id);

	// Get By Id.
	Result getById(final Integer id);

	// get all
	Result listAll(final int clientId);

	// Exists.
	Result exists(final Integer id);

	// Count.
	Result count();

	// List for page and filter.
	Result listPaymentDelaysForPageAndFilter(final int itemsPerPage, final int pageNo,
			final PaymentDelays paymentDelays);

}
