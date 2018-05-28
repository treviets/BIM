package com.redsun.service.service;

import com.redsun.service.entities.Clients;
import com.redsun.service.entities.Result;

/**
 * Clients Service interface
 */
public interface ClientsService {
	
    // Insert.
	Result insert(final Clients clients) ;

    // Update.
	Result update(final Clients clients) ;

    // Delete.
	Result delete(final int id) ;

	// Get By Id.
	Result getById(int clientId, int id) ;

    // Exists.
	Result exists(final int id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listClientsForPageAndFilter(final int itemsPerPage, final int pageNo, final Clients clients);
	
	//list all
	Result listAll();

}
