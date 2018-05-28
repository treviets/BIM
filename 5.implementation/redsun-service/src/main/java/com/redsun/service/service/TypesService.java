package com.redsun.service.service;

import com.redsun.service.entities.Types;
import com.redsun.service.entities.Result;

/**
 * Types Service interface
 */
public interface TypesService {

	// Insert.
	Result insert(final Types types);

	// Update.
	Result update(final Types types);

	// Delete.
	Result delete(final Integer id);

	// Get By Id.
	Result getById(final Integer id);

	// Exists.
	Result exists(final Integer id);

	// Count.
	Result count();

	// List for page and filter.
	Result listTypesForPageAndFilter(final int itemsPerPage, final int pageNo, final Types types);

	// List all types
	Result listAllTypes(final int clientId, final String scope);


}
