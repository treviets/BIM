package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.UnitsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Units;
import com.redsun.service.service.UnitsService;

@Service
public class UnitsServiceImpl implements UnitsService {

	@Autowired
	UnitsDao unitsDao;

	@Override
	public Result listAllUnits(int clientId) {
		final List<Units> data = unitsDao.listAllUnits(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("units", data);
		// Return.
		return new Result(result, true);
	}

}
