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

import com.redsun.service.entities.Clients;
import com.redsun.service.service.ClientsService;
import com.redsun.service.validation.ClientsValidator;

/**
 * Clients Controller
 */
@RestController
@RequestMapping("clientsservice")
public class ClientsController {
	
	// Service.
	@Autowired
	ClientsService clientsService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new ClientsValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Clients clients){
		return clientsService.insert(clients);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") int id, @RequestBody Clients clients){
		return clientsService.update(clients);
	}
	
	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") int id){
		return clientsService.delete(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{clientId}/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("clientId") int clientId, @PathVariable("id") int id) {
		return clientsService.getById(clientId, id);
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listClientsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody Clients clients) {
		return clientsService.listClientsForPageAndFilter(itemsPerPage, pageNo, clients);
	}

	// List all
		@RequestMapping(value = "listall", method = { RequestMethod.GET })
		public Object listAll() {
			return clientsService.listAll();
		}
}
