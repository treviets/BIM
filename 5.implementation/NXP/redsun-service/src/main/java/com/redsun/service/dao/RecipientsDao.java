package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Recipients;

/**
 * Recipients DAO interface
 */
public interface RecipientsDao {
	
    // Insert.
	Integer insert(final Recipients recipients) ;

    // Update.
	int update(final Recipients recipients) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Recipients> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Recipients> listRecipientsForPageAndFilter(final int itemsPerPage, final int pageNo, final Recipients recipients);

}
