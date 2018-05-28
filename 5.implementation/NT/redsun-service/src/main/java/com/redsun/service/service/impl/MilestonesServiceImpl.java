package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.MilestonesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Milestones;
import com.redsun.service.service.MilestonesService;

/**
 * Milestones Service implementation 
 */
@Service
public class MilestonesServiceImpl implements MilestonesService {
	
    @Autowired
    private MilestonesDao milestonesDao;
	
    // Insert.
	@Override
	public Result insert(final Milestones milestones) {
		// Call DAO.
		final int data = milestonesDao.insert(milestones);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Milestones milestones) {
		// Call DAO.
		final int data = milestonesDao.update(milestones);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = milestonesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Milestones> data = milestonesDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("milestones", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = milestonesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = milestonesDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listMilestonesForPageAndFilter(final int itemsPerPage, final int pageNo, final Milestones milestones) {
		// Call DAO.
		final List<Milestones> data = milestonesDao.listMilestonesForPageAndFilter(itemsPerPage, pageNo, milestones);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("milestones", data);
		// Return.
		return new Result(result, true);
	}
	
	// List by projectId.
	public Result listMilestonesByProjectId(final int projectId) {
		// Call DAO.
		final List<Milestones> data = milestonesDao.listMilestonesByProjectId(projectId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("milestones", data);
		// Return.
		return new Result(result, true);
	}

}
