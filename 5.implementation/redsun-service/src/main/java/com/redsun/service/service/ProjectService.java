package com.redsun.service.service;

import com.redsun.service.entities.Project;
import com.redsun.service.entities.Result;

public interface ProjectService {

	// list on page
	Result listProjects(int clientId, int itemsPerPage, int pageNo, Integer... projectType );

	// get by id to edit
	Result getById(int projectId);

	// return list
	Result getByProjectId(int projectId);

	// insert
	Result insert(Project project);

	// get all
	Result listAllProject(int clientId, Integer... projectType);

	// update
	Result update(Project project);

	// delete
	Result delete(int projectId);
	
	//get Gantt chart
	Result getGanttChart(int clientId, int projectId, String userName);
}
