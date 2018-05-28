package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.OrganizationDao;
import com.redsun.service.entities.Organization;
import com.redsun.service.entities.Result;
import com.redsun.service.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	OrganizationDao organizationDao;
	Result result = new Result();


	@Override
	public Result listAll(int clientId) {
		final List<Organization> data = organizationDao.listAll(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("organizations", data);
		// Return.
		return new Result(result, true);
	}
}
