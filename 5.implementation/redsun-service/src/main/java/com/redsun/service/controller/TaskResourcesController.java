package com.redsun.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.Result;
import com.redsun.service.entities.TaskResources;
import com.redsun.service.service.TaskResourcesService;

@RestController
@RequestMapping(value = "taskresources-service")
public class TaskResourcesController {

	@Autowired
	TaskResourcesService taskResourcesService;

	// get by task
	@RequestMapping(value = "/getbytask/{taskId}/{clientId}", method = { RequestMethod.GET })
	public Object getByTask(@PathVariable("taskId") final Integer taskId,
			@PathVariable("clientId") final Integer clientId) {
		return taskResourcesService.getByTask(taskId, clientId);
	}

	// get by project
	@RequestMapping(value = "/gettaskresourcesbyproject/{projectId}/{clientId}", method = { RequestMethod.GET })
	public Object getTaskResourceOneProject(@PathVariable("projectId") final Integer projectId,
			@PathVariable("clientId") final Integer clientId) {
		return taskResourcesService.getTaskResourceOneProject(projectId, clientId);
	}

	// insert
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public List<Object> create(@RequestBody List<TaskResources> listTaskResources) {
		Result result = taskResourcesService.insert(listTaskResources);
		List<Object> results = new ArrayList<Object>();
		results.add(result);
		return results;
	}

	// update
	@RequestMapping(value = "/update/{id}", method = { RequestMethod.PUT })
	public Object update(@RequestBody TaskResources taskResources) {
		return taskResourcesService.update(taskResources);
	}

	// delete
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") final Integer taskResourcesId) {
		return taskResourcesService.delete(taskResourcesId);
	}
}
