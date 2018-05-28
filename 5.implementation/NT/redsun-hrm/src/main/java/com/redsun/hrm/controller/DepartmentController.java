package com.redsun.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.hrm.service.DepartmentService;
import com.redsun.hrm.utils.InformationUtil;

/**
 * Staff Controller
 */
@RestController
@RequestMapping("departmentservice")
public class DepartmentController {
	
	// Service.
	@Autowired
	DepartmentService departmentService;
	@Autowired
	InformationUtil infUtil;
	
	@RequestMapping(value = "list/departments/{userName}/{clientId}", method = { RequestMethod.GET })
	public Object getDepartmentByCom(@PathVariable("userName") String userName, @PathVariable("clientId") int clientId) throws Exception {
		int companyId = infUtil.getCompanyId(userName, clientId);
		return departmentService.getDepartmentByCom(companyId, clientId);
	}

}
