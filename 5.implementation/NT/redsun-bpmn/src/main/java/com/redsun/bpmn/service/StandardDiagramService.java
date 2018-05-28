package com.redsun.bpmn.service;


import com.redsun.bpmn.entities.Result;

/**
 * Phase Service interface
 */
public interface StandardDiagramService {

	// Get standard diagram
	Result getStandardDiagram();
	
	// Get custom diagram
	Result getCustomDiagram();
	

	
}
