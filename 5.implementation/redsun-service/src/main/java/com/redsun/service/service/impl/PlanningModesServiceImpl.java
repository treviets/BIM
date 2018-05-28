package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.PlanningModesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.PlanningModes;
import com.redsun.service.service.PlanningModesService;

/**
 * PlanningModes Service implementation 
 */
@Service
public class PlanningModesServiceImpl implements PlanningModesService {
	
    @Autowired
    private PlanningModesDao planningModesDao;
	
    // Insert.
	@Override
	public Result insert(final PlanningModes planningModes) {
		// Call DAO.
		final int data = planningModesDao.insert(planningModes);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final PlanningModes planningModes) {
		// Call DAO.
		final int data = planningModesDao.update(planningModes);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = planningModesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<PlanningModes> data = planningModesDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("planningModes", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = planningModesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = planningModesDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listPlanningModesForPageAndFilter(final int itemsPerPage, final int pageNo, final PlanningModes planningModes) {
		// Call DAO.
		final List<PlanningModes> data = planningModesDao.listPlanningModesForPageAndFilter(itemsPerPage, pageNo, planningModes);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("planningModes", data);
		// Return.
		return new Result(result, true);
	}
}
