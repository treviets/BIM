package com.redsun.bpmn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.bpmn.dao.PhaseDiagramDao;

import com.redsun.bpmn.entities.Result;
import com.redsun.bpmn.entities.PhaseDiagram;
import com.redsun.bpmn.service.PhaseDiagramService;

/**
 * Phase Diagram Service implementation 
 */
@Service
public class PhaseDiagramServiceImpl implements PhaseDiagramService {
	
    @Autowired
    private PhaseDiagramDao phaseDiagramDao;
    
    // Get all phase diagram
	@Override
	public Result getAllPhaseDiagram() {
		// Call DAO to get all phase diagram
		final List<PhaseDiagram> data = phaseDiagramDao.getPhaseDiagram();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("phase-item", data);
		
		return new Result(result, true);
	}
	
	 // Get all phase diagram by project id
	@Override
	public Result getPhaseDiagramById(int projectId) {
		List<PhaseDiagram> data = phaseDiagramDao.getPhaseDiagramById(projectId);
		
		if (data.size() == 0) {
			data = phaseDiagramDao.getStandardDiagram();
			PhaseDiagram standard= data.get(0);
			standard.setIdProject(projectId);
			phaseDiagramDao.clonePhaseDiagram(standard);
		}
		
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("phase-item", data);
		
		return new Result(result, true);
	}
	
	// Update phase diagram into database
	@Override
	public Result updatePhaseDiagram(PhaseDiagram phaseItem) {
		// Call DAO to update phase item object
		final int data = phaseDiagramDao.updatePhaseDiagram(phaseItem);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);

		return new Result(result, true);
	}
	
	// Create phase diagram into database
	@Override
	public Result createPhaseDiagram(PhaseDiagram phaseDiagram) {
		// Call DAO to update phase item object
		final Map<String, Object> result  = phaseDiagramDao.createPhaseDiagram(phaseDiagram);
		return new Result(result, true);
	}



	
}
