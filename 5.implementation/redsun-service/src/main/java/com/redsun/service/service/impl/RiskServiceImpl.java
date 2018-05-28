package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.RiskDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Risk;
import com.redsun.service.service.RiskService;

@Service
public class RiskServiceImpl implements RiskService {

	@Autowired
	RiskDao riskDao;

	@Override
	public Result getRisks(int clientId, int itemsPerPage, int pageNo) {
		final List<Risk> data = riskDao.listRisk(clientId, itemsPerPage, pageNo);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("risks", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(Risk risk) {
		final int data = riskDao.insert(risk);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("risks", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result update(Risk risk) {
		final int data = riskDao.update(risk);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("risks", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(Integer id) {
		final int data = riskDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("risks", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getById(Integer id) {
		final Risk data = riskDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("risks", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getListRiskOneProject(int clientId, int projectId) {
		final List<Risk> data = riskDao.listRiskOneProject(clientId, projectId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("risks", data);
		// Return.
		return new Result(result, true);
	}

}
