package com.redsun.hrm.dao;

import java.util.List;

import com.redsun.hrm.entities.Departments;


public interface DepartmentDao {
	 List<Departments> getDepartmentByStaff(int id, int clientId);
	 List<Departments> getDepartmentByCom(int companyId, int clientId);
	 List<Departments> getAllDepartments(int clientId);
}
