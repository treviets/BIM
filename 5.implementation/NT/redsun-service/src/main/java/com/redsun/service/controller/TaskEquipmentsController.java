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
import com.redsun.service.entities.TaskEquipments;
import com.redsun.service.service.TaskEquipmentsService;

@RestController
@RequestMapping(value = "taskequipmentservice")
public class TaskEquipmentsController {

	@Autowired
	TaskEquipmentsService taskEquipmentsService;

	// get by project
	@RequestMapping(value = "/getbytask/{taskId}/{projectId}/{clientId}", method = { RequestMethod.GET })
	public Object listAll(@PathVariable("taskId") final Integer taskId,
			@PathVariable("projectId") final Integer projectId, @PathVariable("clientId") final Integer clientId) {
		return taskEquipmentsService.getByTask(taskId, projectId, clientId);

	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public List<Object> create(@RequestBody List<TaskEquipments> listTaskEquipments) {
		Result result = taskEquipmentsService.insert(listTaskEquipments);
		List<Object> results = new ArrayList<Object>();
		results.add(result);
		return results;
	}

	// update planning
	@RequestMapping(value = "/update/{id}", method = { RequestMethod.PUT })
	public Object update(@RequestBody TaskEquipments taskEquipments) {
		return taskEquipmentsService.updateTaskEquipments(taskEquipments);
	}

	// delete
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") final Integer taskEquipmentsId) {
		return taskEquipmentsService.delete(taskEquipmentsId);
	}

}
