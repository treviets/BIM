package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.Severity;
import com.redsun.service.service.SeverityService;

@RestController
@RequestMapping(value = "severities")
public class SeverityController {

	@Autowired
	SeverityService severityService;

	@RequestMapping(value = "/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object getSeverity(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo,
			Model model) {
		return severityService.getSeverity(itemsPerPage, pageNo);
	}

	@RequestMapping(value = "/list/{clientId}", method = { RequestMethod.GET })
	public Object listAllSeverities(@PathVariable("clientId") final Integer clientId, Model model) {
		return severityService.listAllSeverities(clientId);
	}

	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public Object addPriority(@RequestBody Severity severity) {
		return severityService.insert(severity);

	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.POST })
	public Object updatePriority(@PathVariable("id") final Integer id, @RequestBody Severity severity) {
		return severityService.update(severity);

	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
	public Object deletePriority(@PathVariable("id") final Integer id) {
		return severityService.delete(id);
	}
}
