package com.redsun.hrm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.hrm.dao.DepartmentDao;
import com.redsun.hrm.dao.mapper.DepartmentMapper;
import com.redsun.hrm.entities.Departments;


@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	static private String SELECT_DEPARMENT_BY_STAFF = "SELECT id, code, name, parent_id, wbs, level, manager, '' as chief_name, '' as img"
											+ " FROM departments WHERE wbs like ? AND client_id = ? AND is_trash = '0' ORDER BY level";
	static private String SELECT_DEPARMENT_BY_COM = "SELECT d.id, d.code, d.name, d.parent_id, d.wbs, d.level, r.full_name as chief_name, r.img"
			+ " FROM departments d INNER JOIN resources r ON d.manager = r.id"
			+ " WHERE d.company_id=? AND d.client_id = ? AND d.is_trash = '0' ORDER BY d.level";
	static private String SELECT_ALL_DEPARMENTS = "SELECT id, code, name, parent_id, wbs, level, '' as chief_name, '' as img"
											+ " FROM departments WHERE client_id = ? AND is_trash = '0' ORDER BY level";
	private static Logger log = Logger.getLogger(DepartmentDaoImpl.class);

	public List<Departments> getDepartmentByStaff(int id, int clientId) {
		// TODO Auto-generated method stub
		String wbs = String.valueOf(id) + "%";
		List<Departments> department = null;
		List<Object> params = new ArrayList<Object>();
		params.add(wbs);
		params.add(clientId);
		try {
			department = jdbcTemplate.query(SELECT_DEPARMENT_BY_STAFF, params.toArray(), new DepartmentMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return department;
	}
	
	public List<Departments> getDepartmentByCom(int companyId, int clientId) {
		// TODO Auto-generated method stub
		List<Departments> department = null;
		List<Object> params = new ArrayList<Object>();
		params.add(companyId);
		params.add(clientId);
		try {
			department = jdbcTemplate.query(SELECT_DEPARMENT_BY_COM, params.toArray(), new DepartmentMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return department;
	}

	@Override
	public List<Departments> getAllDepartments(int clientId) {
		// TODO Auto-generated method stub
		List<Departments> listDepartments = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		try {
			listDepartments = jdbcTemplate.query(SELECT_ALL_DEPARMENTS, params.toArray(), new DepartmentMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listDepartments;
	}
}
