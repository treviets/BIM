package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.CalendarDefinitions;

/**
 * CalendarDefinitions DAO interface
 */
public interface CalendarDefinitionsDao {
	
    // Insert.
	Integer insert(final CalendarDefinitions calendarDefinitions) ;

    // Update.
	int update(final CalendarDefinitions calendarDefinitions) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<CalendarDefinitions> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<CalendarDefinitions> listCalendarDefinitionsForPageAndFilter(final int itemsPerPage, final int pageNo, final CalendarDefinitions calendarDefinitions);

}
