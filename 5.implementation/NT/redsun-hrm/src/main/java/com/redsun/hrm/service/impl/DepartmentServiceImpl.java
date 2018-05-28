package com.redsun.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.hrm.dao.DepartmentDao;
import com.redsun.hrm.entities.Departments;
import com.redsun.hrm.entities.Result;
import com.redsun.hrm.service.DepartmentService;

/**
 * Resources Service implementation
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public Result getDepartmentByCom(int companyId, int clientId) {
		final List<Departments> listDepartments = departmentDao.getDepartmentByCom(companyId, clientId);
		
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("listDepartments", listDepartments);
		// Return.
		return new Result(result, true);
	}
}
