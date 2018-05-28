package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.CalendarDefinitionsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.CalendarDefinitions;
import com.redsun.service.service.CalendarDefinitionsService;

/**
 * CalendarDefinitions Service implementation 
 */
@Service
public class CalendarDefinitionsServiceImpl implements CalendarDefinitionsService {
	
    @Autowired
    private CalendarDefinitionsDao calendarDefinitionsDao;
	
    // Insert.
	@Override
	public Result insert(final CalendarDefinitions calendarDefinitions) {
		// Call DAO.
		final int data = calendarDefinitionsDao.insert(calendarDefinitions);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final CalendarDefinitions calendarDefinitions) {
		// Call DAO.
		final int data = calendarDefinitionsDao.update(calendarDefinitions);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = calendarDefinitionsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<CalendarDefinitions> data = calendarDefinitionsDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("calendarDefinitions", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = calendarDefinitionsDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = calendarDefinitionsDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listCalendarDefinitionsForPageAndFilter(final int itemsPerPage, final int pageNo, final CalendarDefinitions calendarDefinitions) {
		// Call DAO.
		final List<CalendarDefinitions> data = calendarDefinitionsDao.listCalendarDefinitionsForPageAndFilter(itemsPerPage, pageNo, calendarDefinitions);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("calendarDefinitions", data);
		// Return.
		return new Result(result, true);
	}
}
