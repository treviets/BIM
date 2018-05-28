package com.redsun.bpmn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.redsun.bpmn.dao.PhaseDocumentDao;
import com.redsun.bpmn.entities.Result;
import com.redsun.bpmn.entities.PhaseDocument;
import com.redsun.bpmn.service.PhaseDocumentService;

/**
 * Phase Service implementation 
 */
@Service
public class PhaseDocumentServiceImpl implements PhaseDocumentService {
	
    @Autowired
    private PhaseDocumentDao phaseDocumentDao;
    
	// Get all document of project
	@Override
	public Result getAllPhaseDocument(int projectId) {
		// Call DAO to get document for project
		final List<PhaseDocument> data = phaseDocumentDao.getAllPhaseDocument(projectId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("phase-document", data);
		
		return new Result(result, true);
	}

	// Get all document of step
	@Override
	public Result getAllPhaseDocumentForStep(int projectId, int activeStep, int activeSubStep) {
		// Call DAO to get document for step
		final List<PhaseDocument> data = phaseDocumentDao.getAllPhaseDocumentForStep(projectId, activeStep, activeSubStep);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("phase-document", data);
		
		return new Result(result, true);
	}

	// Update document of step
	@Override
	public Result updatePhaseForStep(PhaseDocument phaseDocument) {
		// Call DAO to update phase item object
		final int data = phaseDocumentDao.update(phaseDocument);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);

		return new Result(result, true);
	}

	@Override
	public Result insertDocumentForStep(PhaseDocument phaseDocument) {
		final int data = phaseDocumentDao.insert(phaseDocument);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("documents", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result deleteDocumentForStep(PhaseDocument phaseDocument) {
		final int data = phaseDocumentDao.delete(phaseDocument);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("documents", data);
		// Return.
		return new Result(result, true);
	
	}


	
}
