package com.redsun.hrm.dao;

import java.util.List;

import com.redsun.hrm.entities.Staffs;


public interface StaffDao {
	 List<Staffs> getAllStaffs(int clientId);
}
