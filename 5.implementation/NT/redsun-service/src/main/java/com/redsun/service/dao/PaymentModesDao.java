package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.PaymentModes;

/**
 * PaymentModes DAO interface
 */
public interface PaymentModesDao {
	
    // Insert.
	Integer insert(final PaymentModes paymentModes) ;

    // Update.
	int update(final PaymentModes paymentModes) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<PaymentModes> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<PaymentModes> listPaymentModesForPageAndFilter(final int itemsPerPage, final int pageNo, final PaymentModes paymentModes);

}
