package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.BaseLines;
import com.redsun.service.service.ProjectBaseLineService;

@RestController
@RequestMapping(value = "projectbaseline")
public class ProjectBaseLineController {

	@Autowired
	ProjectBaseLineService projectBaseLineService;

	@RequestMapping(value = "/list/labor/{clientId}/{projectId}/{baselineId}", method = { RequestMethod.GET })
	public Object getProjectLaborBaseLine(@PathVariable("clientId") int clientId, @PathVariable("projectId") int projectId,
			@PathVariable("baselineId") int baselineId) {
		return projectBaseLineService.getLaborBaseline(projectId, clientId, baselineId);
	}
	@RequestMapping(value = "/insertbatch/labor/{clientId}/{projectId}", method = { RequestMethod.POST })
	public Object insertLaborBaseLine(@RequestBody BaseLines baseline, @PathVariable("clientId") int clientId, @PathVariable("projectId") int projectId) {
		return projectBaseLineService.insertLaborBaseline(projectId, clientId, baseline);
	}
	@RequestMapping(value = "/insertbatch/material/{clientId}/{projectId}", method = { RequestMethod.POST })
	public Object insertMaterialBaseLine(@RequestBody BaseLines baseline, @PathVariable("clientId") int clientId, @PathVariable("projectId") int projectId) {
		return projectBaseLineService.insertMaterialBaseline(projectId, clientId, baseline);
	}
	@RequestMapping(value = "/insertbatch/equipment/{clientId}/{projectId}", method = { RequestMethod.POST })
	public Object insertEquipmentBaseLine(@RequestBody BaseLines baseline, @PathVariable("clientId") int clientId, @PathVariable("projectId") int projectId) {
		return projectBaseLineService.insertEquipmentBaseline(projectId, clientId, baseline);
	}
	@RequestMapping(value = "/insertbatch/task/{clientId}/{projectId}", method = { RequestMethod.POST })
	public Object insertTaskBaseLine(@RequestBody BaseLines baseline, @PathVariable("clientId") int clientId, @PathVariable("projectId") int projectId) {
		return projectBaseLineService.insertTaskBaseline(projectId, clientId, baseline);
	}
	@RequestMapping(value = "/insertbatch/taskresource/{clientId}/{projectId}", method = { RequestMethod.POST })
	public Object insertTaskResourceBaseLine(@RequestBody BaseLines baseline, @PathVariable("clientId") int clientId, @PathVariable("projectId") int projectId) {
		return projectBaseLineService.insertTaskResourceBaseline(projectId, clientId, baseline);
	}
	@RequestMapping(value = "/insertbatch/taskmaterial/{clientId}/{projectId}", method = { RequestMethod.POST })
	public Object insertTaskMaterialBaseLine(@RequestBody BaseLines baseline, @PathVariable("clientId") int clientId, @PathVariable("projectId") int projectId) {
		return projectBaseLineService.insertTaskMaterialBaseline(projectId, clientId, baseline);
	}
	@RequestMapping(value = "/insertbatch/taskequipment/{clientId}/{projectId}", method = { RequestMethod.POST })
	public Object insertTaskEquimentBaseLine(@RequestBody BaseLines baseline, @PathVariable("clientId") int clientId, @PathVariable("projectId") int projectId) {
		return projectBaseLineService.insertTaskEquipmentBaseline(projectId, clientId, baseline);
	}
}
