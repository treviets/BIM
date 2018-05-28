package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.VersionProjects;

/**
 * VersionProjects DAO interface
 */
public interface VersionProjectsDao {
	
    // Insert.
	Integer insert(final VersionProjects versionProjects) ;

    // Update.
	int update(final VersionProjects versionProjects) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<VersionProjects> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<VersionProjects> listVersionProjectsForPageAndFilter(final int itemsPerPage, final int pageNo, final VersionProjects versionProjects);

}
