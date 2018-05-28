package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.Calendars;
import com.redsun.service.service.CalendarsService;

@RestController
@RequestMapping(value = "calendar")
public class CalendarController {
	
	@Autowired
	CalendarsService calendarsService;
	
	@RequestMapping(value = "/list/{clientId}", method = { RequestMethod.GET })
	public Object listCalendars(@PathVariable("clientId") Integer clientId) {
		return calendarsService.getCalendars(clientId);
		
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object addCalendar(Model model,@RequestBody Calendars calendar) {
		return calendarsService.insert(calendar);
	}
	

}
