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

import com.redsun.service.entities.BillLines;
import com.redsun.service.service.BillLinesService;
import com.redsun.service.validation.BillLinesValidator;

/**
 * BillLines Controller
 */
@RestController
@RequestMapping("billlinesservice")
public class BillLinesController {
	
	// Service.
	@Autowired
	BillLinesService billLinesService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new BillLinesValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody BillLines billLines){
		return billLinesService.insert(billLines);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody BillLines billLines){
		return billLinesService.update(billLines);
	}
	
	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id){
		return billLinesService.delete(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return billLinesService.getById(id);
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listBillLinesForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody BillLines billLines) {
		return billLinesService.listBillLinesForPageAndFilter(itemsPerPage, pageNo, billLines);
	}

}
