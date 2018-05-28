package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ProfilesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Profiles;
import com.redsun.service.service.ProfilesService;

/**
 * Profiles Service implementation 
 */
@Service
public class ProfilesServiceImpl implements ProfilesService {
	
    @Autowired
    private ProfilesDao profilesDao;
	
    // Insert.
	@Override
	public Result insert(final Profiles profiles) {
		// Call DAO.
		final int data = profilesDao.insert(profiles);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Profiles profiles) {
		// Call DAO.
		final int data = profilesDao.update(profiles);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = profilesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Profiles> data = profilesDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("profiles", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = profilesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = profilesDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listProfilesForPageAndFilter(final int itemsPerPage, final int pageNo, final Profiles profiles) {
		// Call DAO.
		final List<Profiles> data = profilesDao.listProfilesForPageAndFilter(itemsPerPage, pageNo, profiles);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("profiles", data);
		// Return.
		return new Result(result, true);
	}
}
