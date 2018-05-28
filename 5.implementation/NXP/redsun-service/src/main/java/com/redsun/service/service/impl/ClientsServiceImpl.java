package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ClientsDao;
import com.redsun.service.entities.Clients;
import com.redsun.service.entities.Result;
import com.redsun.service.service.ClientsService;
import com.redsun.service.utils.RedSunConstants;

/**
 * Clients Service implementation 
 */
@Service
public class ClientsServiceImpl implements ClientsService {
	
    @Autowired
    private ClientsDao clientsDao;
	
    // Insert.
	@Override
	public Result insert(final Clients clients) {
		// Call DAO.
		final int data = clientsDao.insert(clients);
		final Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("id", data);
		String message = "Duplicate Client Code";
		if(data > 0){
			message = RedSunConstants.STATUS_SUCCESS_STRING;
		}
		Result result = new Result(data, message,  resultMap);
		
		// Return.
		return result;
	}

    // Update.
	@Override
	public Result update(final Clients clients) {
		// Call DAO.
		final int data = clientsDao.update(clients);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final int id) {
		// Call DAO.
		final int data = clientsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(int clientId, int id) {
		// Call DAO.
		final List<Clients> data = clientsDao.getById(clientId, id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("clients", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final int id) {
		// Call DAO.
		final boolean data = clientsDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = clientsDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listClientsForPageAndFilter(final int itemsPerPage, final int pageNo, final Clients clients) {
		// Call DAO.
		final List<Clients> data = clientsDao.listClientsForPageAndFilter(itemsPerPage, pageNo, clients);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("clients", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listAll() {
		final List<Clients> data = clientsDao.listClients();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("clients", data);
		// Return.
		return new Result(result, true);
	}
}
