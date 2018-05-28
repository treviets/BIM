package com.redsun.service.service;

import java.util.Map;

import com.redsun.service.entities.Commands;
import com.redsun.service.entities.Result;

/**
 * Commands Service interface
 */
public interface CommandsService {
	
    // Insert.
	Result insert(final Commands commands) ;

    // Update.
	Result update(final Commands commands) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listCommandsForPageAndFilter(final int itemsPerPage, final int pageNo, final Commands commands);

	// List extend for page and filter.
	Result listCommandsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter);

}
