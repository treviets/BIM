package com.redsun.service.entities;

public class ResourceAssignedToProjects {
	
	private int id;
	private int projectId;
	private Project projects;
	private int resourceId;
	private Resource resources;
	private int teamId;
	//private Team teams;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the listProjects
	 */
	public Project getProjects() {
		return projects;
	}
	/**
	 * @param listProjects the listProjects to set
	 */
	public void setProjects(Project projects) {
		this.projects = projects;
	}
	/**
	 * @return the resourceId
	 */
	public int getResourceId() {
		return resourceId;
	}
	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	/**
	 * @return the listResources
	 */
	public Resource getResources() {
		return resources;
	}
	/**
	 * @param listResources the listResources to set
	 */
	public void setResources(Resource resources) {
		this.resources = resources;
	}
	/**
	 * @return the teamId
	 */
	public int getTeamId() {
		return teamId;
	}
	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	
			
}
