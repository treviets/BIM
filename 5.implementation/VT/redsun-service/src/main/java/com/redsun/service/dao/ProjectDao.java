package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Project;

public interface ProjectDao {

	// get all projects
	List<Project> listAllProjects(int clientId, Integer... projectType);

	// get projects on page
	List<Project> listProjects(int clientId, int itemsPerPage, int pageNo, Integer... projectType);

	// get by id to edit
	Project getById(int projectId);

	// get by id projects
	List<Project> getByProjectId(int projectId);

	// insert
	int insert(Project project);

	// edit
	int update(Project project);

	// delete
	int delete(int projectId);
}
