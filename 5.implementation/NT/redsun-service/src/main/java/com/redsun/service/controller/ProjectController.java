package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.Project;
import com.redsun.service.service.ProjectService;
import com.redsun.service.utils.TypesConstants;

@RestController
@RequestMapping(value = "projects")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@RequestMapping(value = "/list/{clientId}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object getProjects(@PathVariable("clientId") int clientId, @PathVariable("itemsPerPage") int itemsPerPage,
			@PathVariable("pageNo") int pageNo) {
		return projectService.listProjects(clientId, itemsPerPage, pageNo);
	}
	
	@RequestMapping(value = "/design/list/{clientId}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object getDesignProjects(@PathVariable("clientId") int clientId, @PathVariable("itemsPerPage") int itemsPerPage,
			@PathVariable("pageNo") int pageNo) {
		return projectService.listProjects(clientId, itemsPerPage, pageNo, TypesConstants.DESIGN_PROJECT_TYPE);
	}

	@RequestMapping(value = "/list/{clientId}", method = { RequestMethod.GET })
	public Object getAll(@PathVariable("clientId") int clientId) {
		return projectService.listAllProject(clientId);
	}

	@RequestMapping(value = "/list/by-resource/{resourceCode}/", method = { RequestMethod.GET })
	public Object getAllProjectByResourceId(@PathVariable("resourceCode") String resourceCode) {
		return projectService.getProjectByResourceId(resourceCode);
	}
	
	// return list
	@RequestMapping(value = "/getbyprojectid/{projectId}", method = { RequestMethod.GET })
	public Object getByProjectId(@PathVariable("projectId") int projectId) {
		return projectService.getByProjectId(projectId);
	}

	@RequestMapping(value = "/getbyid/{projectId}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("projectId") final Integer projectId) {
		return projectService.getById(projectId);
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object create(@RequestBody Project project) {
		project.setProjectType(TypesConstants.BUILD_PROJECT_TYPE);
		return projectService.insert(project);
	}
	
	@RequestMapping(value = "/design/add", method = { RequestMethod.POST })
	public Object createDesignProject(@RequestBody Project project) {
		project.setProjectType(TypesConstants.DESIGN_PROJECT_TYPE);
		return projectService.insert(project);
	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") final Integer id, @RequestBody Project project) {
		return projectService.update(project);

	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") final Integer id) {
		return projectService.delete(id);
	}

	@RequestMapping(value = "/ganttchart/data/{clientId}/{projectId}/{userName}", method = { RequestMethod.GET })
	public Object getGanttData(@PathVariable("clientId") final Integer clientId,
			@PathVariable("projectId") final Integer projectId, @PathVariable("userName") final String userName) {
		return projectService.getGanttChart(clientId, projectId, userName);
	}

}
