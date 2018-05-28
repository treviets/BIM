package com.redsun.bpmn.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.bpmn.entities.PhaseDiagram;
import com.redsun.bpmn.service.PhaseDiagramService;



/**
 * Phase Diagram Controller
 */
@RestController
@RequestMapping("phasediagram")
public class PhaseDiagramController {
	
	// Service.
	@Autowired
	PhaseDiagramService phaseDiagramService;
	
	// Get all Phase Diagram
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public Object getAllPhaseDiagram() {
		return phaseDiagramService.getAllPhaseDiagram();
	}
	
	// Get Phase Diagram by Id
	@RequestMapping(value = "/list/{id}", method = { RequestMethod.GET })
	public Object getPhaseDiagramById(@PathVariable("id") Integer id) {
		return phaseDiagramService.getPhaseDiagramById(id);
	}
	
	// Create Phase Diagram
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object createPhaseDiagram(@RequestBody PhaseDiagram phaseDiagram){
		return phaseDiagramService.createPhaseDiagram(phaseDiagram);
	}

	
}
