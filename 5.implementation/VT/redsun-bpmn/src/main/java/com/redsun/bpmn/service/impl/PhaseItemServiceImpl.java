package com.redsun.bpmn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.bpmn.dao.PhaseItemDao;
import com.redsun.bpmn.entities.PhaseItem;
import com.redsun.bpmn.entities.Result;
import com.redsun.bpmn.service.PhaseItemService;

/**
 * Phase Service implementation 
 */
@Service
public class PhaseItemServiceImpl implements PhaseItemService {
	
    @Autowired
    private PhaseItemDao phaseItemDao;
    
    // Get all phase item
	@Override
	public Result getAllPhaseItem() {
		// Call DAO to get all phase item
		final List<PhaseItem> data = phaseItemDao.getAllPhaseItem();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("phase-item", data);
		
		return new Result(result, true);
	}
	
	 // Get all phase item by project id
	@Override
	public Result getAllPhaseItemById(int projectId) {
		final List<PhaseItem> data = phaseItemDao.getAllPhaseItemById(projectId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("phase-item", data);
		
		return new Result(result, true);
	}
	
	// Update phase item into database
	@Override
	public Result update(PhaseItem phaseItem, String currentStepId, String actionType) {
		// Call DAO to update phase item object
		final Map<String, Object> result = phaseItemDao.update(phaseItem, currentStepId, actionType);	
		return new Result(result, true);
	}


	
}
