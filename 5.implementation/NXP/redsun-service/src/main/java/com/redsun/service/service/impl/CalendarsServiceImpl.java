package com.redsun.service.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.CalendarsDao;
import com.redsun.service.entities.Calendars;
import com.redsun.service.entities.Result;
import com.redsun.service.service.CalendarsService;

@Service
public class CalendarsServiceImpl implements CalendarsService{
		@Autowired
		CalendarsDao calendarDao;
		
	@Override
	public Result getCalendars(int clientId) {
		final List<Calendars> data = calendarDao.listCalendars(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("calendars", data);
		
		
		// Return.
		return new Result(result, true);

	}

	/*@Override
	public Result getCalendarOfOneProject(int clientId, int projectId) {
		// TODO Auto-generated method stub
		final List<Calendars> data = calendarDao.listCalendarOfOneProject(clientId, projectId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("calendars", data);
		// Return.
		return new Result(result, true);
	}*/

	@Override
	public Result insert(Calendars calendar) {
		final int data = calendarDao.insert(calendar);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("calendars", data);
		// Return.
		return new Result(result, true);
	}

/*	@Override
	public Result update(Calendars calendar) {
		final int data = calendarDao.update(calendar);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("calendars", data);
		// Return.
		return new Result(result, true);
	}*/

	/*@Override
	public Result delete(int id) {
		final int data = calendarDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("calendars", data);
		// Return.
		return new Result(result, true);
	}*/

/*	@Override
	public Result getById(int id) {
		final Calendars data = calendarDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("calendars", data);
		// Return.
		return new Result(result, true);
	}*/

}
