package com.redsun.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.hrm.dao.DepartmentDao;
import com.redsun.hrm.dao.PositionDao;
import com.redsun.hrm.dao.StaffDao;
import com.redsun.hrm.entities.Departments;
import com.redsun.hrm.entities.Positions;
import com.redsun.hrm.entities.Result;
import com.redsun.hrm.entities.Staffs;
import com.redsun.hrm.service.StaffService;

/**
 * Resources Service implementation
 */
@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffDao staffDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private PositionDao positionDao;
	
	@Override
	public Result getAllStaffs(int clientId) {
		final List<Staffs> listStaffs = staffDao.getAllStaffs(clientId);
		final List<Departments> listDepartments = departmentDao.getAllDepartments(clientId);
		final List<Positions> listPositions = positionDao.getAllPositions(clientId);
		for(int i=0; i<listStaffs.size(); i++){
			for(int j=0; j<listDepartments.size(); j++)
				if(Integer.valueOf(listStaffs.get(i).getDeparmentId())==listDepartments.get(j).getId()){
					listStaffs.get(i).setDepartment(listDepartments.get(j));
					break;
				}
			for(int k=0; k<listPositions.size(); k++)
				if(Integer.valueOf(listStaffs.get(i).getPositionId())==listPositions.get(k).getId()){
					listStaffs.get(i).setPosition(listPositions.get(k));
					break;
				}
		}
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("listStaffs", listStaffs);
		// Return.
		return new Result(result, true);
	}
}
