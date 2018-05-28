package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.BillLines;

/**
 * BillLines DAO interface
 */
public interface BillLinesDao {
	
    // Insert.
	Integer insert(final BillLines billLines) ;

    // Update.
	int update(final BillLines billLines) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<BillLines> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<BillLines> listBillLinesForPageAndFilter(final int itemsPerPage, final int pageNo, final BillLines billLines);

}
