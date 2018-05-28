package com.redsun.service.dao;



import java.util.List;

import com.redsun.service.entities.Calendars;

public interface CalendarsDao {
	
	//on page
	List<Calendars> listCalendars (int clientId);
	//insert
	int insert(Calendars calendar);
	
	//update
//	int update(Calendars calendar);
	
	//delete
//	int delete(int id);
	
	//get by id
//	Calendars getById(int id);
	
	// select all tasks of one the project
//	List<Calendars> listCalendarOfOneProject(int clientId, int projectId);


}
