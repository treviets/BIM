package com.redsun.service.service;

import java.util.Map;

import com.redsun.service.entities.Roles;
import com.redsun.service.entities.Result;

/**
 * Roles Service interface
 */
public interface RolesService {

	// Insert.
	Result insert(final Roles roles);

	// Update.
	Result update(final Roles roles);

	// Delete.
	Result delete(final Integer id);

	// Get By Id.
	Result getById(final Integer id);

	// Exists.
	Result exists(final Integer id);

	// Count.
	Result count();

	// List for page and filter.
	Result listRolesForPageAndFilter(final int itemsPerPage, final int pageNo, final Roles roles);

	// List extend for page and filter.
	Result listRolesExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter);

	// get all
	Result getAll(int clientId);
}
