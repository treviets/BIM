package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Profiles;

/**
 * Profiles DAO interface
 */
public interface ProfilesDao {
	
    // Insert.
	Integer insert(final Profiles profiles) ;

    // Update.
	int update(final Profiles profiles) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Profiles> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Profiles> listProfilesForPageAndFilter(final int itemsPerPage, final int pageNo, final Profiles profiles);

}
