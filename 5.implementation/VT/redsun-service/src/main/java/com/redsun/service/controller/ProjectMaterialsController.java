package com.redsun.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.ProjectMaterials;
import com.redsun.service.entities.Result;
import com.redsun.service.service.ProjectMaterialsService;

@RestController
@RequestMapping(value = "projectmaterialservice")
public class ProjectMaterialsController {

	@Autowired
	ProjectMaterialsService projectMaterialsService;

	// get by project
	@RequestMapping(value = "/getbyproject/{projectId}/{clientId}", method = { RequestMethod.GET })
	public Object listAll(@PathVariable("projectId") final Integer projectId,
			@PathVariable("clientId") final Integer clientId) {
		return projectMaterialsService.getByProjectId(projectId, clientId);
	}

	// insert
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public List<Object> create(@RequestBody List<ProjectMaterials> listProjectMaterials) {
		Result result = projectMaterialsService.insert(listProjectMaterials);
		List<Object> results = new ArrayList<Object>();
		results.add(result);
		return results;
	}

	// filter materials were exist in project_materials
	@RequestMapping(value = "/filterprojectmaterial/{projectId}/{taskId}/{clientId}", method = { RequestMethod.GET })
	public Object filterProjectMaterial(@PathVariable("projectId") final Integer projectId,
			@PathVariable("taskId") final Integer taskId, @PathVariable("clientId") final Integer clientId) {
		return projectMaterialsService.filterProjectMaterials(projectId, taskId, clientId);
	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.PUT })
	public Object updatePMaterials(@PathVariable("id") final Integer id,
			@RequestBody ProjectMaterials projectMaterials) {
		return projectMaterialsService.updatePMaterial(projectMaterials);

	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") final Integer projectMaterialId) {
		return projectMaterialsService.delete(projectMaterialId);
	}
}
