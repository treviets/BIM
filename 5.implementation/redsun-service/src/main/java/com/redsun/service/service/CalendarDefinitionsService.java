package com.redsun.service.service;

import com.redsun.service.entities.CalendarDefinitions;
import com.redsun.service.entities.Result;

/**
 * CalendarDefinitions Service interface
 */
public interface CalendarDefinitionsService {
	
    // Insert.
	Result insert(final CalendarDefinitions calendarDefinitions) ;

    // Update.
	Result update(final CalendarDefinitions calendarDefinitions) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listCalendarDefinitionsForPageAndFilter(final int itemsPerPage, final int pageNo, final CalendarDefinitions calendarDefinitions);

}
