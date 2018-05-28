package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Products;

/**
 * Products DAO interface
 */
public interface ProductsDao {
	
    // Insert.
	Integer insert(final Products products) ;

    // Update.
	int update(final Products products) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Products> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Products> listProductsForPageAndFilter(final int itemsPerPage, final int pageNo, final Products products);

}
