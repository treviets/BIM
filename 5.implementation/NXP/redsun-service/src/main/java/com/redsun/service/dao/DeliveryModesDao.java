package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.DeliveryModes;

/**
 * DeliveryModes DAO interface
 */
public interface DeliveryModesDao {
	
    // Insert.
	Integer insert(final DeliveryModes deliveryModes) ;

    // Update.
	int update(final DeliveryModes deliveryModes) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<DeliveryModes> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<DeliveryModes> listDeliveryModesForPageAndFilter(final int itemsPerPage, final int pageNo, final DeliveryModes deliveryModes);

}
