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

import com.redsun.service.entities.Statuses;
import com.redsun.service.service.StatusesService;
import com.redsun.service.validation.StatusesValidator;

/**
 * Statuses Controller
 */
@RestController
@RequestMapping("statusesservice")
public class StatusesController {

	// Service.
	@Autowired
	StatusesService statusesService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder) {
		binder.setValidator(new StatusesValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Statuses statuses) {
		return statusesService.insert(statuses);
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody Statuses statuses) {
		return statusesService.update(statuses);
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id) {
		return statusesService.delete(id);
	}

	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return statusesService.getById(id);
	}

	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listStatusesForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage,
			@PathVariable("pageNo") int pageNo, @RequestBody Statuses statuses) {
		return statusesService.listStatusesForPageAndFilter(itemsPerPage, pageNo, statuses);
	}

	// List for all
	@RequestMapping(value = "list/{clientId}", method = { RequestMethod.GET })
	public Object listAllStatuses(@PathVariable("clientId") final Integer clientId) {
		return statusesService.listAllStatuses(clientId);
	}

	// List use for task
	@RequestMapping(value = "getbyscope/{clientId}/{scope}", method = { RequestMethod.GET })
	public Object getByScope(@PathVariable("clientId") final Integer clientId, @PathVariable("scope") final String scope) {
		return statusesService.getByScope(clientId, scope);
	}

}
