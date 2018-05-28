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

import com.redsun.service.entities.Tenders;
import com.redsun.service.service.TendersService;
import com.redsun.service.validation.TendersValidator;

import java.io.IOException;

/**
 * Tenders Controller
 */
@RestController
@RequestMapping("tenders-service")
public class TendersController {
	
	@Autowired
	private TendersService tendersService;

	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new TendersValidator());
	}

	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Tenders tenders){
		return tendersService.insert(tenders);
	}
	
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody Tenders tenders){
		return tendersService.update(tenders);
	}
	
	@RequestMapping(value = "delete/{refType}/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id, @PathVariable("refType") final String refType) throws IOException {
		return tendersService.delete(id, refType);
	}
	
	@RequestMapping(value = "get/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return tendersService.getById(id);
	}
	
	@RequestMapping(value = "list/{clientId}/{pageNo}/{itemsPerPage}", method = { RequestMethod.GET })
	public Object listTendersForPageAndFilter(@PathVariable final Integer clientId, @PathVariable final Integer pageNo, @PathVariable final Integer itemsPerPage) {
		return tendersService.listTendersForPageAndFilter(itemsPerPage, pageNo, new Tenders());
	}
}
