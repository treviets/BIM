package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Types;

/**
 * Types DAO interface
 */
public interface TypesDao {
	
    // Insert.
	Integer insert(final Types types) ;

    // Update.
	int update(final Types types) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Types> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Types> listTypesForPageAndFilter(final int itemsPerPage, final int pageNo, final Types types);
	
	//List all type
	List<Types> listAllTypes(final int clientId, final String scope);
	


}

