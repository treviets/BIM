package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.PriorityDao;
import com.redsun.service.entities.Priority;
import com.redsun.service.entities.Result;
import com.redsun.service.service.PriorityService;

@Service
public class PriorityServiceImpl implements PriorityService {

	@Autowired
	PriorityDao priorityDao;
	Result result = new Result();

	@Override
	public Result getPriority(int client_id, String name, int itemsPerPage, int pageNo) {
		final List<Priority> data = priorityDao.listPriority(client_id, name, itemsPerPage, pageNo);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("priorities", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(Priority priority) {
		final int data = priorityDao.insert(priority);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("priorities", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result update(Priority priority) {
		final int data = priorityDao.update(priority);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("priorities", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(Integer id) {
		final int data = priorityDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("priorities", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getById(Integer id) {
		// Call DAO.
		final Priority data = priorityDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("priorities", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listAllPriorities(int clientId) {
		final List<Priority> data = priorityDao.listAllPriories(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("priorities", data);
		// Return.
		return new Result(result, true);
	}
}
