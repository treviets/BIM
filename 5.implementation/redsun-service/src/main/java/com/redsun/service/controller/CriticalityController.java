package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.Criticality;
import com.redsun.service.service.CriticalityService;

@RestController
@RequestMapping(value = "criticalities")
public class CriticalityController {

	@Autowired
	CriticalityService criticalityService;

	@RequestMapping(value = "/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object getPriority(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo,
			Model model) {
		return criticalityService.getCriticality(itemsPerPage, pageNo);
	}

	// List all
	@RequestMapping(value = "/list/{clientId}", method = { RequestMethod.GET })
	public Object listAll(@PathVariable ("clientId") final Integer clientId) {
		return criticalityService.listAll(clientId);
	}

	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public Object addCriticality(@RequestBody Criticality criticality) {
		return criticalityService.insert(criticality);

	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.POST })
	public Object updatePriority(@PathVariable("id") final Integer id, @RequestBody Criticality criticality) {
		return criticalityService.update(criticality);

	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
	public Object deletePriority(@PathVariable("id") final Integer id) {
		return criticalityService.delete(id);
	}
}
