package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Clients;

/**
 * Clients DAO interface
 */
public interface ClientsDao {
	
    // Insert.
	int insert(final Clients clients) ;

    // Update.
	int update(final Clients clients) ;

    // Delete.
	int delete(final int id) ;

	// Get By Id.
	List<Clients> getById(int clientId, int id) ;

    // Exists.
	boolean exists(final int id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Clients> listClientsForPageAndFilter(final int itemsPerPage, final int pageNo, final Clients clients);
	
	//list all
	List<Clients> listClients();

}
