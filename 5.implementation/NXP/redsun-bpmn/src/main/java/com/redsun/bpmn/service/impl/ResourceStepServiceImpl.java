package com.redsun.bpmn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.bpmn.dao.ResourceStepDao;
import com.redsun.bpmn.entities.Result;
import com.redsun.bpmn.entities.ResourceStep;
import com.redsun.bpmn.service.ResourceStepService;

/**
 * ResourceStep Service implementation 
 */
@Service
public class ResourceStepServiceImpl implements ResourceStepService {
	
    @Autowired
    private ResourceStepDao resourceStepDao;
    
	// Get all document of step
	@Override
	public Result getAllResourceStep(int projectId, String stepId) {
		final List<ResourceStep> data = resourceStepDao.getAllResourceForStep(projectId, stepId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("resource-step", data);
		
		return new Result(result, true);
	}
	
	// Update resource of step
	@Override
	public Result updateResourceForStep(ResourceStep resourceStep) {
		final int data = resourceStepDao.update(resourceStep);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);

		return new Result(result, true);
	}

	// Insert resource of step
	@Override
	public Result insertResourceForStep(ResourceStep resourceStep) {
		final int data = resourceStepDao.insert(resourceStep);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("resource-step", data);

		return new Result(result, true);
	}

	@Override
	public Result deleteResourceForStep(int projectId, String stepId, String code) {
		final int data = resourceStepDao.delete(projectId, stepId, code);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("resource-step", data);

		return new Result(result, true);
	}
}
