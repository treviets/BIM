package com.redsun.service.dao;

import java.util.List;
import java.util.Map;

import com.redsun.service.entities.Bills;

/**
 * Bills DAO interface
 */
public interface BillsDao {
	
    // Insert.
	Integer insert(final Bills bills) ;

    // Update.
	int update(final Bills bills) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Bills> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Bills> listBillsForPageAndFilter(final int itemsPerPage, final int pageNo, final Bills bills);

	// List extend for page and filter.
	List<Map<Object, Object>> listBillsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter);

}
