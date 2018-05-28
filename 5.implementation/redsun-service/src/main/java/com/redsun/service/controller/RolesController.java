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

import com.redsun.service.entities.Roles;
import com.redsun.service.service.RolesService;
import com.redsun.service.validation.RolesValidator;

/**
 * Roles Controller
 */
@RestController
@RequestMapping("roles-service")
public class RolesController {

	// Service.
	@Autowired
	RolesService rolesService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder) {
		binder.setValidator(new RolesValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Roles roles) {
		return rolesService.insert(roles);
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody Roles roles) {
		return rolesService.update(roles);
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id) {
		return rolesService.delete(id);
	}

	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return rolesService.getById(id);
	}

	// Get all
	@RequestMapping(value = "listall/{clientId}", method = { RequestMethod.GET })
	public Object getAll(@PathVariable("clientId") final Integer clientId) {
		return rolesService.getAll(clientId);
	}

	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listRolesForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage,
			@PathVariable("pageNo") int pageNo, @RequestBody Roles roles) {
		return rolesService.listRolesForPageAndFilter(itemsPerPage, pageNo, roles);
	}

	// List extend for page and filter.
	@RequestMapping(value = "list-extend/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listRolesExtendForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage,
			@PathVariable("pageNo") int pageNo, @RequestBody Map<String, Object> filter) {
		return rolesService.listRolesExtendForPageAndFilter(itemsPerPage, pageNo, filter);
	}

}
