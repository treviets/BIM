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

import com.redsun.service.entities.Bills;
import com.redsun.service.service.BillsService;
import com.redsun.service.validation.BillsValidator;

/**
 * Bills Controller
 */
@RestController
@RequestMapping("bills-service")
public class BillsController {
	
	// Service.
	@Autowired
	BillsService billsService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new BillsValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Bills bills){
		return billsService.insert(bills);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody Bills bills){
		return billsService.update(bills);
	}
	
	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id){
		return billsService.delete(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return billsService.getById(id);
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listBillsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody Bills bills) {
		return billsService.listBillsForPageAndFilter(itemsPerPage, pageNo, bills);
	}
	
	// List extend for page and filter.
	@RequestMapping(value = "list-extend/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listBillsExtendForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody Map<String, Object> filter) {
		return billsService.listBillsExtendForPageAndFilter(itemsPerPage, pageNo, filter);
	}

}
