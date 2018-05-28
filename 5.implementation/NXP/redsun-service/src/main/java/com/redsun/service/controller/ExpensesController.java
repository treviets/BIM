package com.redsun.service.controller;

import com.redsun.service.entities.Expenses;
import com.redsun.service.service.ExpensesService;
import com.redsun.service.validation.ExpensesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("expenses-service")
public class ExpensesController {
	
	@Autowired
	private ExpensesService expensesService;

	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new ExpensesValidator());
	}

	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@RequestBody final Expenses expenses){
		return expensesService.insert(expenses);
	}
	
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable final Integer id, @RequestBody Expenses expenses){
		return expensesService.update(expenses);
	}
	
	@RequestMapping(value = "delete/{scope}/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable final String scope, @PathVariable final Integer id) throws IOException {
		return expensesService.delete(scope, id);
	}
	
	@RequestMapping(value = "getById/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable final Integer id) {
		return expensesService.getById(id);
	}
	
	@RequestMapping(value = "list/{clientId}/{scope}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object listExpensesForPageAndFilter(@PathVariable final Integer clientId, @PathVariable final String scope,
											   @PathVariable final Integer itemsPerPage, @PathVariable final Integer pageNo) {
		return expensesService.listExpensesForPageAndFilter(clientId, scope, pageNo, itemsPerPage);
	}

}
