package com.redsun.service.service;

import com.redsun.service.entities.Products;
import com.redsun.service.entities.Result;

/**
 * Products Service interface
 */
public interface ProductsService {
	
    // Insert.
	Result insert(final Products products) ;

    // Update.
	Result update(final Products products) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listProductsForPageAndFilter(final int itemsPerPage, final int pageNo, final Products products);

}
