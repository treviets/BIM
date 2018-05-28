package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Resources;

/**
 * Resources DAO interface
 */
public interface ResourcesDao {

	// Insert.
	Integer insert(final Resources resources);

	// Update.
	int update(final Resources resources);

	// Delete.
	int delete(final Integer id);
	
	int restore(final Integer resourceId, final Integer clientId);

	// Get By Id.
	Resources getById(final int clientId, final int id);

	// Exists.
	boolean exists(final Integer id);

	// Count.
	long count(int clientId);

	// List for page and filter.
	List<Resources> listResourcesForPageAndFilter(final int clientId, final int itemsPerPage, final int pageNo);

	// List for exterior
	List<Resources> listResourcesExterior(final int clientId, final int itemsPerPage, final int pageNo);

	// Get all
	List<Resources> getAll(final Integer clientId, Integer... deletedFlag);
	
	// Get all
	List<Resources> getAllForAllType(final Integer clientId, Integer... deletedFlag);

	// filter by members were exist in project
	List<Resources> filterMember(int projectId, int clientId);

	// List by code.
	List<Resources> getByCode(String code, int clientId);

	// filter title = worker
	List<Resources> filterTitle(int clientId);

	// Get all to validate exist
	List<Resources> getToExist(int clientId, int resourceId);

	List<Resources> getForBPMN(String code);

}
