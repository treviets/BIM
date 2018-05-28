package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.TypesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Types;
import com.redsun.service.service.TypesService;

/**
 * Types Service implementation
 */
@Service
public class TypesServiceImpl implements TypesService {

	@Autowired
	private TypesDao typesDao;

	// Insert.
	@Override
	public Result insert(final Types types) {
		// Call DAO.
		final int data = typesDao.insert(types);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Update.
	@Override
	public Result update(final Types types) {
		// Call DAO.
		final int data = typesDao.update(types);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = typesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Types> data = typesDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("types", data);
		// Return.
		return new Result(result, true);
	}

	// Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = typesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

	// Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = typesDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listTypesForPageAndFilter(final int itemsPerPage, final int pageNo, final Types types) {
		// Call DAO.
		final List<Types> data = typesDao.listTypesForPageAndFilter(itemsPerPage, pageNo, types);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("types", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listAllTypes(int clientId, String scope) {
		final List<Types> data = typesDao.listAllTypes(clientId, scope);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("types", data);
		// Return.
		return new Result(result, true);
	}
}
