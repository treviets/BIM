package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.CriticalityDao;
import com.redsun.service.entities.Criticality;
import com.redsun.service.entities.Result;
import com.redsun.service.service.CriticalityService;

@Service
public class CriticalityServiceImpl implements CriticalityService {

	@Autowired
	CriticalityDao criticalityDao;

	@Override
	public Result getCriticality(int itemsPerPage, int pageNo) {
		final List<Criticality> data  = criticalityDao.listCriticality(itemsPerPage, pageNo);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("criticalities", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listAll(int clientId) {
		final List<Criticality> data  = criticalityDao.listAllCriticalities(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("criticalities", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(Criticality criticality) {
		final int data = criticalityDao.insert(criticality);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("criticalities", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result update(Criticality criticality) {
		final int data = criticalityDao.update(criticality);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("criticalities", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(Integer id) {
		final int data = criticalityDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("criticalities", data);
		// Return.
		return new Result(result, true);
	}
	}
