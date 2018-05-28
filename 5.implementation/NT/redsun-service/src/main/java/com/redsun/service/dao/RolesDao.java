package com.redsun.service.dao;

import java.util.List;
import java.util.Map;

import com.redsun.service.entities.Roles;

/**
 * Roles DAO interface
 */
public interface RolesDao {

	// Insert.
	Integer insert(final Roles roles);

	// Update.
	int update(final Roles roles);

	// Delete.
	int delete(final Integer id);

	// Get By Id.
	List<Roles> getById(final Integer id);

	// Get all
	List<Roles> getAll(int clientId);

	// Exists.
	boolean exists(final Integer id);

	// Count.
	long count();

	// List for page and filter.
	List<Roles> listRolesForPageAndFilter(final int itemsPerPage, final int pageNo, final Roles roles);

	// List extend for page and filter.
	List<Map<Object, Object>> listRolesExtendForPageAndFilter(final int itemsPerPage, final int pageNo,
			final Map<String, Object> filter);

}
