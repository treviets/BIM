package com.redsun.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.ProjectResources;
import com.redsun.service.entities.Result;
import com.redsun.service.service.ProjectResourcesService;

@RestController
@RequestMapping(value = "projectresourceservice")
public class ProjectResourcesController {

	@Autowired
	ProjectResourcesService projectResourcesService;

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public List<Object> create(@RequestBody List<ProjectResources> projectResources) {
		Result result = projectResourcesService.insert(projectResources);
		List<Object> results = new ArrayList<Object>();
		results.add(result);
		return results;
	}

	// get by projectId
	@RequestMapping(value = "/getbyproject/{projectId}/{clientId}", method = { RequestMethod.GET })
	public Object getByProject(@PathVariable("projectId") final Integer projectId,
			@PathVariable("clientId") final Integer clientId) {
		return projectResourcesService.getByProject(projectId, clientId);
	}

	// filter resources were exist in project_resources
	@RequestMapping(value = "/filterprojectresource/{projectId}/{taskId}/{clientId}", method = { RequestMethod.GET })
	public Object filterProjectResources(@PathVariable("projectId") final Integer projectId,
			@PathVariable("taskId") final Integer taskId, @PathVariable("clientId") final Integer clientId) {
		return projectResourcesService.filterProjectResources(projectId, taskId, clientId);
	}

	// get all
	@RequestMapping(value = "/getallprojectresources/{clientId}", method = { RequestMethod.GET })
	public Object getAllProjectResources(@PathVariable("clientId") final Integer clientId) {
		return projectResourcesService.getAllProjectResources(clientId);
	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.PUT })
	public Object updateRisk(@PathVariable("id") final Integer id, @RequestBody ProjectResources projectResources) {
		return projectResourcesService.update(projectResources);

	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") final Integer projectResourcesId) {
		return projectResourcesService.delete(projectResourcesId);
	}
}
