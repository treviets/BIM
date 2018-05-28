package com.redsun.bpmn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.bpmn.dao.DocumentStepDao;
import com.redsun.bpmn.entities.Result;
import com.redsun.bpmn.entities.DocumentStep;
import com.redsun.bpmn.service.DocumentStepService;

/**
 * Phase Service implementation 
 */
@Service
public class DocumentStepServiceImpl implements DocumentStepService {
	
    @Autowired
    private DocumentStepDao documentStepDao;
    
	// Get all document of step
	@Override
	public Result getAllDocumentStep(int projectId, String stepId) {
		// Call DAO to get document for step
		final List<DocumentStep> data = documentStepDao.getAllDocumentForStep(projectId, stepId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("document-step", data);
		
		return new Result(result, true);
	}
	
	// Update document of step
	@Override
	public Result updateDocumentForStep(DocumentStep documentStep) {
		// Call DAO to update phase item object
		final int data = documentStepDao.update(documentStep);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);

		return new Result(result, true);
	}

	// Insert document of step
	@Override
	public Result insertDocumentForStep(DocumentStep documentStep) {
		final int data = documentStepDao.insert(documentStep);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("document-step", data);
		// Return.
		return new Result(result, true);
	}
	
	// Delete document of step
	@Override
	public Result deleteDocumentForStep(DocumentStep documentStep) {
		final int data = documentStepDao.delete(documentStep);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("document-step", data);
		// Return.
		return new Result(result, true);
	}
}
