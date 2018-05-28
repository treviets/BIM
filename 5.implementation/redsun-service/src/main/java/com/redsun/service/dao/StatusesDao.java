package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Statuses;

/**
 * Statuses DAO interface
 */
public interface StatusesDao {

	// Insert.
	Integer insert(final Statuses statuses);

	// Update.
	int update(final Statuses statuses);

	// Delete.
	int delete(final Integer id);

	// Get By Id.
	List<Statuses> getById(final Integer id);

	// Exists.
	boolean exists(final Integer id);

	// Count.
	long count();

	// List for page and filter.
	List<Statuses> listStatusesForPageAndFilter(final int itemsPerPage, final int pageNo, final Statuses statuses);

	// List all.
	List<Statuses> listAllStatuses(int clientId);

	// list statuses use for Task
	List<Statuses> getByScope(int clientId, String scope);

}
