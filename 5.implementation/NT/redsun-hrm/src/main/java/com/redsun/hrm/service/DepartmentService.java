package com.redsun.hrm.service;

import com.redsun.hrm.entities.Result;

/**
 * Resources Service interface
 */
public interface DepartmentService {
	Result getDepartmentByCom(int companyId, int clientId);

}
