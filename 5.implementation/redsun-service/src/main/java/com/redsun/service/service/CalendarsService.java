package com.redsun.service.service;

import com.redsun.service.entities.Calendars;
import com.redsun.service.entities.Result;


public interface CalendarsService {
	
	//select
	Result getCalendars(int clientId);
	
	// select calendar of one the project
//	Result getCalendarOfOneProject(int clientId, int projectId);

	// insert
	Result insert(Calendars calendar);

	// update
//	Result update(Calendars calendar);

	// delete
//	Result delete(int id);

	// delete
//	Result getById(int id);
	
	

}
