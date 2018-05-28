package com.redsun.service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.Commands;
import com.redsun.service.service.CommandsService;
import com.redsun.service.validation.CommandsValidator;

/**
 * Commands Controller
 */
@RestController
@RequestMapping("commands-service")
public class CommandsController {
	
	// Service.
	@Autowired
	CommandsService commandsService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new CommandsValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Commands commands){
		return commandsService.insert(commands);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody Commands commands){
		return commandsService.update(commands);
	}
	
	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id){
		return commandsService.delete(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return commandsService.getById(id);
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listCommandsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody Commands commands) {
		return commandsService.listCommandsForPageAndFilter(itemsPerPage, pageNo, commands);
	}
	
	// List extend for page and filter.
	@RequestMapping(value = "list-extend/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listCommandsExtendForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody Map<String, Object> filter) {
		return commandsService.listCommandsExtendForPageAndFilter(itemsPerPage, pageNo, filter);
	}

}
