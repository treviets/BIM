package com.redsun.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.Configurations;
import com.redsun.service.service.ConfigurationsService;

@RestController
@RequestMapping(value = "configurations-service")
public class ConfigurationsController {

	@Autowired
	ConfigurationsService configurationsService;

	// insert
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object create(@RequestBody List<Configurations> listConfigurations) {
		return configurationsService.insert(listConfigurations);
	}
	
	// get by project
		@RequestMapping(value = "/getbyproject/{projectId}/{clientId}", method = { RequestMethod.GET })
		public Object getByTask(@PathVariable("projectId") final Integer projectId, @PathVariable("clientId") final Integer clientId) {
			return configurationsService.getByProject(projectId, clientId);
		}

	// update
	@RequestMapping(value = "/update/{projectId}/{clientId}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("projectId") final Integer projectId, @PathVariable("clientId") final Integer clientId, @RequestBody List<Configurations> listConfigurations) {
		return configurationsService.update(listConfigurations);
	}

}
