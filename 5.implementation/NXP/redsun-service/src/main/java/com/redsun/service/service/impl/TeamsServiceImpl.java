package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.TeamsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Teams;
import com.redsun.service.service.TeamsService;

/**
 * Teams Service implementation 
 */
@Service
public class TeamsServiceImpl implements TeamsService {
	
    @Autowired
    private TeamsDao teamsDao;
	
    // Insert.
	@Override
	public Result insert(final Teams teams) {
		// Call DAO.
		final int data = teamsDao.insert(teams);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Teams teams) {
		// Call DAO.
		final int data = teamsDao.update(teams);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = teamsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Teams> data = teamsDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("teams", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = teamsDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = teamsDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listTeamsForPageAndFilter(final int itemsPerPage, final int pageNo, final Teams teams) {
		// Call DAO.
		final List<Teams> data = teamsDao.listTeamsForPageAndFilter(itemsPerPage, pageNo, teams);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("teams", data);
		// Return.
		return new Result(result, true);
	}
}
