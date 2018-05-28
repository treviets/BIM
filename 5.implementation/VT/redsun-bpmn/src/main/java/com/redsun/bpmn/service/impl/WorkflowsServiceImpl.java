package com.redsun.bpmn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.bpmn.dao.WorkflowsDao;
import com.redsun.bpmn.entities.Result;
import com.redsun.bpmn.entities.Workflows;
import com.redsun.bpmn.service.WorkflowsService;

/**
 * Workflows Service implementation 
 */
@Service
public class WorkflowsServiceImpl implements WorkflowsService {
	
    @Autowired
    private WorkflowsDao workflowsDao;
	
    // Insert.
	@Override
	public Result insert(final Workflows workflows) {
		// Call DAO.
		final int data = workflowsDao.insert(workflows);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Workflows workflows) {
		// Call DAO.
		final int data = workflowsDao.update(workflows);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = workflowsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Workflows> data = workflowsDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("workflows", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = workflowsDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = workflowsDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listWorkflowsForPageAndFilter(final int itemsPerPage, final int pageNo, final Workflows workflows) {
		// Call DAO.
		final List<Workflows> data = workflowsDao.listWorkflowsForPageAndFilter(itemsPerPage, pageNo, workflows);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("workflows", data);
		// Return.
		return new Result(result, true);
	}
}
