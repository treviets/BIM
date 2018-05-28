package com.redsun.bpmn.entities;

/* Resource Step
 * Represent for document each of step
 */

public class ResourceStep extends AbstractEntity {
	
	private int projectId;
	private String stepId;
	private String resourceCode;
	private String emailOfResponsible;

	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	public String getEmailOfResponsible() {
		return emailOfResponsible;
	}
	public void setEmailOfResponsible(String emailOfResponsible) {
		this.emailOfResponsible = emailOfResponsible;
	}
	
}

