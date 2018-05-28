package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.CalendarDefinitions;
import com.redsun.service.service.CalendarDefinitionsService;
import com.redsun.service.validation.CalendarDefinitionsValidator;

/**
 * CalendarDefinitions Controller
 */
@RestController
@RequestMapping("calendardefinitionsservice")
public class CalendarDefinitionsController {
	
	// Service.
	@Autowired
	CalendarDefinitionsService calendarDefinitionsService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new CalendarDefinitionsValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody CalendarDefinitions calendarDefinitions){
		return calendarDefinitionsService.insert(calendarDefinitions);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody CalendarDefinitions calendarDefinitions){
		return calendarDefinitionsService.update(calendarDefinitions);
	}
	
	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id){
		return calendarDefinitionsService.delete(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return calendarDefinitionsService.getById(id);
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listCalendarDefinitionsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody CalendarDefinitions calendarDefinitions) {
		return calendarDefinitionsService.listCalendarDefinitionsForPageAndFilter(itemsPerPage, pageNo, calendarDefinitions);
	}

}
