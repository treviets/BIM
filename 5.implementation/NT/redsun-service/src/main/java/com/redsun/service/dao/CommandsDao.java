package com.redsun.service.dao;

import java.util.List;
import java.util.Map;

import com.redsun.service.entities.Commands;

/**
 * Commands DAO interface
 */
public interface CommandsDao {
	
    // Insert.
	Integer insert(final Commands commands) ;

    // Update.
	int update(final Commands commands) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Commands> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Commands> listCommandsForPageAndFilter(final int itemsPerPage, final int pageNo, final Commands commands);

	// List extend for page and filter.
	List<Map<Object, Object>> listCommandsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter);

}
