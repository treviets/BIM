package com.redsun.bpmn.entities;

/* Phase entity
 * Represent each of phase of any flows in Design process
 */

public class StandardDiagram extends AbstractEntity {
	
	private int idProject;
	private String activeStep;
	private String contain;
	
	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public int getIdProject() {
		return this.idProject;
	}
	
	public void setActiveStep(String activeStep) {
		this.activeStep = activeStep;
	}
	
	public String getActiveStep() {
		return this.activeStep;
	}
	
	public void setContain(String contain) {
		this.contain = contain;
	}
	
	public String getContain() {
		return this.contain;
	}
	
}

