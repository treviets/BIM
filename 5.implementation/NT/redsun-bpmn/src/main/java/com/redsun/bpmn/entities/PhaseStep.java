package com.redsun.bpmn.entities;

import java.util.List;

/* Phase step
 * Represent each of phase of any flows in Design process
 */

public class PhaseStep extends AbstractEntity {
	
	private int index;
	private int idProject;
	private String activeStep;
	private String idStep;
	private String name;
	private String contain;
	private PhaseStep nextStep;
	private String parentStep;
	private String previousStep;
	private List<PhaseStep> subSteps;
	private List<String> incoming;
	private List<String> outgoing;
	private List<ResourceStep> resource;
	private List<DocumentStep> document;
	
	public void setIndex(int index) {
		this.index = index;
	}
	public Integer getIndex() {
		return this.index;
	}
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
	public void setIdStep(String idStep) {
		this.idStep = idStep;
	}
	public String getIdStep() {
		return this.idStep;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setContain(String contain) {
		this.contain = contain;
	}
	public String getContain() {
		return this.contain;
	}
	public void setSubSteps(List<PhaseStep> subSteps) {
		this.subSteps = subSteps;
	}
	public List<PhaseStep> getSubSteps() {
		return this.subSteps;
	}
	public void setImcoming(List<String> incoming) {
		this.incoming = incoming;
	}
	public List<String> getIncoming() {
		return this.incoming;
	}
	public void setOutgoing(List<String> outgoing) {
		this.outgoing = outgoing;
	}
	public List<String> getOutgoing() {
		return this.outgoing;
	}
	public void setResource(List<ResourceStep> resource) {
		this.resource = resource;
	}
	public List<ResourceStep> getResource() {
		return this.resource;
	}
	public void setDocument(List<DocumentStep>  document) {
		this.document = document;
	}
	public List<DocumentStep> getDocument() {
		return this.document;
	}
	/**
	 * @return the nextStep
	 */
	public PhaseStep getNextStep() {
		return nextStep;
	}
	/**
	 * @param nextStep the nextStep to set
	 */
	public void setNextStep(PhaseStep nextStep) {
		this.nextStep = nextStep;
	}
	/**
	 * @return the previousStep
	 */
	public String getPreviousStep() {
		return previousStep;
	}
	/**
	 * @param previousStep the previousStep to set
	 */
	public void setPreviousStep(String previousStep) {
		this.previousStep = previousStep;
	}
	/**
	 * @param incoming the incoming to set
	 */
	public void setIncoming(List<String> incoming) {
		this.incoming = incoming;
	}

	public String getParentStep() {
		return parentStep;
	}

	public void setParentStep(String parentStep) {
		this.parentStep = parentStep;
	}
	

}

