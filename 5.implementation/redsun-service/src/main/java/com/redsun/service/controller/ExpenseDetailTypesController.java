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

import com.redsun.service.entities.ExpenseDetailTypes;
import com.redsun.service.service.ExpenseDetailTypesService;
import com.redsun.service.validation.ExpenseDetailTypesValidator;

/**
 * ExpenseDetailTypes Controller
 */
@RestController
@RequestMapping("expensedetailtypesservice")
public class ExpenseDetailTypesController {
	
	// Service.
	@Autowired
	ExpenseDetailTypesService expenseDetailTypesService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new ExpenseDetailTypesValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody ExpenseDetailTypes expenseDetailTypes){
		return expenseDetailTypesService.insert(expenseDetailTypes);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody ExpenseDetailTypes expenseDetailTypes){
		return expenseDetailTypesService.update(expenseDetailTypes);
	}
	
	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id){
		return expenseDetailTypesService.delete(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return expenseDetailTypesService.getById(id);
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listExpenseDetailTypesForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody ExpenseDetailTypes expenseDetailTypes) {
		return expenseDetailTypesService.listExpenseDetailTypesForPageAndFilter(itemsPerPage, pageNo, expenseDetailTypes);
	}

}
