package com.redsun.service.service;

import com.redsun.service.entities.Statuses;
import com.redsun.service.entities.Result;

/**
 * Statuses Service interface
 */
public interface StatusesService {

	// Insert.
	Result insert(final Statuses statuses);

	// Update.
	Result update(final Statuses statuses);

	// Delete.
	Result delete(final Integer id);

	// Get By Id.
	Result getById(final Integer id);

	// Exists.
	Result exists(final Integer id);

	// Count.
	Result count();

	// List for page and filter.
	Result listStatusesForPageAndFilter(final int itemsPerPage, final int pageNo, final Statuses statuses);

	// List for page and filter.
	Result listAllStatuses(int clientId);

	// List use for task
	Result getByScope(int clientId, String scope);
}
