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

import com.redsun.service.entities.Referencables;
import com.redsun.service.service.ReferencablesService;
import com.redsun.service.validation.ReferencablesValidator;

/**
 * Referencables Controller
 */
@RestController
@RequestMapping("referencablesservice")
public class ReferencablesController {
	
	// Service.
	@Autowired
	ReferencablesService referencablesService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new ReferencablesValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Referencables referencables){
		return referencablesService.insert(referencables);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody Referencables referencables){
		return referencablesService.update(referencables);
	}
	
	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id){
		return referencablesService.delete(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return referencablesService.getById(id);
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listReferencablesForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody Referencables referencables) {
		return referencablesService.listReferencablesForPageAndFilter(itemsPerPage, pageNo, referencables);
	}

}
