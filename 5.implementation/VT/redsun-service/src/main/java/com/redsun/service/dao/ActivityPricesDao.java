package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.ActivityPrices;

/**
 * ActivityPrices DAO interface
 */
public interface ActivityPricesDao {
	
    // Insert.
	Integer insert(final ActivityPrices activityPrices) ;

    // Update.
	int update(final ActivityPrices activityPrices) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<ActivityPrices> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<ActivityPrices> listActivityPricesForPageAndFilter(final int itemsPerPage, final int pageNo, final ActivityPrices activityPrices);

}
