package com.redsun.service.dao;

import java.util.List;
import java.util.Map;

import com.redsun.service.entities.Payments;

/**
 * Payments DAO interface
 */
public interface PaymentsDao {
	
    // Insert.
	Integer insert(final Payments payments) ;

    // Update.
	int update(final Payments payments) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Payments> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Payments> listPaymentsForPageAndFilter(final int itemsPerPage, final int pageNo, final Payments payments);

	// List extend for page and filter.
	List<Map<Object, Object>> listPaymentsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter);

}
