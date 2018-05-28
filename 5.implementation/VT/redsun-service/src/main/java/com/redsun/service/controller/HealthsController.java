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

import com.redsun.service.entities.Healths;
import com.redsun.service.service.HealthsService;
import com.redsun.service.validation.HealthsValidator;

/**
 * Healths Controller
 */
@RestController
@RequestMapping("healthsservice")
public class HealthsController {
	
	// Service.
	@Autowired
	HealthsService healthsService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new HealthsValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Healths healths){
		return healthsService.insert(healths);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody Healths healths){
		return healthsService.update(healths);
	}
	
	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id){
		return healthsService.delete(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return healthsService.getById(id);
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listHealthsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody Healths healths) {
		return healthsService.listHealthsForPageAndFilter(itemsPerPage, pageNo, healths);
	}

}
