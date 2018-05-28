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

import com.redsun.service.entities.ExpenseDetails;
import com.redsun.service.service.ExpenseDetailsService;
import com.redsun.service.validation.ExpenseDetailsValidator;

/**
 * ExpenseDetails Controller
 */
@RestController
@RequestMapping("expense-details-service")
public class ExpenseDetailsController {
	
	// Service.
	@Autowired
	private ExpenseDetailsService expenseDetailsService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new ExpenseDetailsValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody ExpenseDetails expenseDetails){
		return expenseDetailsService.insert(expenseDetails);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody ExpenseDetails expenseDetails){
		return expenseDetailsService.update(expenseDetails);
	}
	
	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id){
		return expenseDetailsService.delete(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getById/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return expenseDetailsService.getById(id);
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/{clientId}/{expensesId}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object listExpenseDetailsForPageAndFilter(@PathVariable Integer clientId, @PathVariable Integer expensesId, @PathVariable Integer itemsPerPage, @PathVariable Integer pageNo) {
		return expenseDetailsService.listExpenseDetailsByExpensesId(clientId, expensesId, itemsPerPage, pageNo);
	}

}
