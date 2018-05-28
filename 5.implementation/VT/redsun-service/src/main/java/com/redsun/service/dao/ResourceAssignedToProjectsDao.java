package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.ResourceAssignedToProjects;

public interface ResourceAssignedToProjectsDao {
	 List<ResourceAssignedToProjects> getResourceByProject(int projectId);
}
