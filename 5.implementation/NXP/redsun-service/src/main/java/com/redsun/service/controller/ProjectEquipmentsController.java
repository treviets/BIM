package com.redsun.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.ProjectEquipments;
import com.redsun.service.entities.Result;
import com.redsun.service.service.ProjectEquipmentsService;

@RestController
@RequestMapping(value = "projectequipmentservice")
public class ProjectEquipmentsController {

	@Autowired
	ProjectEquipmentsService projectEquipmentsService;

	// get by project
	@RequestMapping(value = "/getbyproject/{projectId}/{clientId}", method = { RequestMethod.GET })
	public Object listAll(@PathVariable("projectId") final Integer projectId,
			@PathVariable("clientId") final Integer clientId) {
		return projectEquipmentsService.getByProjectId(projectId, clientId);
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public List<Object> create(@RequestBody List<ProjectEquipments> listProjectEquipments) {
		Result result = projectEquipmentsService.insert(listProjectEquipments);
		List<Object> results = new ArrayList<Object>();
		results.add(result);
		return results;
	}

	// filter equipments were exist in project_equipments
	@RequestMapping(value = "/filterprojectequipment/{projectId}/{taskId}/{clientId}", method = { RequestMethod.GET })
	public Object filterProjectMaterial(@PathVariable("projectId") final Integer projectId,
			@PathVariable("taskId") final Integer taskId, @PathVariable("clientId") final Integer clientId) {
		return projectEquipmentsService.filterProjectEquipments(projectId, taskId, clientId);
	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.PUT })
	public Object updatePEquipments(@PathVariable("id") final Integer id,
			@RequestBody ProjectEquipments projectEquipments) {
		return projectEquipmentsService.update(projectEquipments);

	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") final Integer projectEquipmentsId) {
		return projectEquipmentsService.delete(projectEquipmentsId);
	}

}
