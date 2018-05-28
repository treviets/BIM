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

import com.redsun.service.entities.CronExecutions;
import com.redsun.service.service.CronExecutionsService;
import com.redsun.service.validation.CronExecutionsValidator;

/**
 * CronExecutions Controller
 */
@RestController
@RequestMapping("cronexecutionsservice")
public class CronExecutionsController {
	
	// Service.
	@Autowired
	CronExecutionsService cronExecutionsService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new CronExecutionsValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody CronExecutions cronExecutions){
		return cronExecutionsService.insert(cronExecutions);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody CronExecutions cronExecutions){
		return cronExecutionsService.update(cronExecutions);
	}
	
	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id){
		return cronExecutionsService.delete(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return cronExecutionsService.getById(id);
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listCronExecutionsForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody CronExecutions cronExecutions) {
		return cronExecutionsService.listCronExecutionsForPageAndFilter(itemsPerPage, pageNo, cronExecutions);
	}

}
