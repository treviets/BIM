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
import com.redsun.service.entities.TaskMaterials;
import com.redsun.service.service.TaskMaterialsService;

@RestController
@RequestMapping(value = "taskmaterialservice")
public class TaskMaterialsController {

	@Autowired
	TaskMaterialsService taskMaterialsService;

	// get by project
	@RequestMapping(value = "/getbytask/{taskId}/{projectId}/{clientId}", method = { RequestMethod.GET })
	public Object listAll(@PathVariable("taskId") final Integer taskId,
			@PathVariable("projectId") final Integer projectId, @PathVariable("clientId") final Integer clientId) {
		return taskMaterialsService.getByTask(taskId, projectId, clientId);
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public List<Object> create(@RequestBody List<TaskMaterials> listTaskMaterials) {
		Result result = taskMaterialsService.insert(listTaskMaterials);
		List<Object> results = new ArrayList<Object>();
		results.add(result);
		return results;
	}

	// update actual
	@RequestMapping(value = "/update/{id}", method = { RequestMethod.PUT })
	public Object update(@RequestBody TaskMaterials taskMaterial) {
		return taskMaterialsService.update(taskMaterial);
	}

	// update when planning
	@RequestMapping(value = "/updateplanning/{id}", method = { RequestMethod.PUT })
	public Object updateTaskResources(@RequestBody TaskMaterials taskMaterials) {
		return taskMaterialsService.updateTaskMaterials(taskMaterials);
	}

	// delete
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") final Integer taskMaterialsId) {
		return taskMaterialsService.delete(taskMaterialsId);
	}

}
