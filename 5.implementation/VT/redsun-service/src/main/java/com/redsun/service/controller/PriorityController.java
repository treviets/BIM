package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.Priority;
import com.redsun.service.service.PriorityService;

@RestController
@RequestMapping(value = "priorities")
public class PriorityController {

	@Autowired
	PriorityService priorityService;

	@RequestMapping(value = "/{client_id}/{name}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object getPriority(@PathVariable("client_id") int client_id, @PathVariable("name") String name,
			@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, Model model) {
		return priorityService.getPriority(client_id, name, itemsPerPage, pageNo);
	}

	@RequestMapping(value = "/list/{clientId}", method = { RequestMethod.GET })
	public Object listAllPriorities(@PathVariable("clientId") final Integer clientId) {
		return priorityService.listAllPriorities(clientId);
	}
	// insert
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object addPriority(final Priority priority) {
		return priorityService.insert(priority);

	}

	// update
	@RequestMapping(value = "/update/{id}", method = { RequestMethod.PUT})
	public Object updatePriority(@PathVariable("id") final Integer id, @RequestBody Priority priority) {
		return priorityService.update(priority);

	}

	// delete
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
	public Object deletePriority(@PathVariable("id") final Integer id) {
		return priorityService.delete(id);
	}

	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") final Integer id) {
		return priorityService.getById(id);
	}

}
