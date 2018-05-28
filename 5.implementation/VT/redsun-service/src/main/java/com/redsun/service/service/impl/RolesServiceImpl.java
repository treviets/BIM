package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.RolesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Roles;
import com.redsun.service.service.RolesService;

/**
 * Roles Service implementation 
 */
@Service
public class RolesServiceImpl implements RolesService {
	
    @Autowired
    private RolesDao rolesDao;
    
	
    // Insert.
	@Override
	public Result insert(final Roles roles) {
		// Call DAO.
		final int roleId = rolesDao.insert(roles);
	
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", roleId);
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Roles roles) {
		// Call DAO.
		final int roleId = rolesDao.update(roles);
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", roleId);
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer roleId) {
		// Call DAO.
		final int data = rolesDao.delete(roleId);
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer roleId) {
		// Call DAO.
		final List<Roles> data = rolesDao.getById(roleId);
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("roles", data);
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer roleId) {
		// Call DAO.
		final boolean data = rolesDao.exists(roleId);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = rolesDao.count();

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listRolesForPageAndFilter(final int itemsPerPage, final int pageNo, final Roles roles) {
		// Call DAO.
		final List<Roles> data = rolesDao.listRolesForPageAndFilter(itemsPerPage, pageNo, roles);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("roles", data);
		return new Result(result, true);
	}

	// List extend for page and filter.
	public Result listRolesExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter) {
		// Call DAO.
		final List<Map<Object, Object>> data = rolesDao.listRolesExtendForPageAndFilter(itemsPerPage, pageNo, filter);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("roles", data);
		return new Result(result, true);
		
	}

	@Override
	public Result getAll(int clientId) {
		// Call DAO.
		final List<Roles> data = rolesDao.getAll(clientId);
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("roles", data);
		return new Result(result, true);
	}

}
