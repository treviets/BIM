package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.PaymentDelays;

/**
 * PaymentDelays DAO interface
 */
public interface PaymentDelaysDao {

	// Insert.
	Integer insert(final PaymentDelays paymentDelays);

	// Update.
	int update(final PaymentDelays paymentDelays);

	// Delete.
	int delete(final Integer id);

	// Get By Id.
	List<PaymentDelays> getById(final Integer id);

	// get all
	List<PaymentDelays> listAll(int clientId);

	// Exists.
	boolean exists(final Integer id);

	// Count.
	long count();

	// List for page and filter.
	List<PaymentDelays> listPaymentDelaysForPageAndFilter(final int itemsPerPage, final int pageNo,
			final PaymentDelays paymentDelays);

}
