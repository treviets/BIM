package com.redsun.hrm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.hrm.dao.DepartmentDao;
import com.redsun.hrm.dao.InformationDao;
import com.redsun.hrm.dao.mapper.DepartmentMapper;
import com.redsun.hrm.dao.mapper.InformationMapper;
import com.redsun.hrm.entities.Departments;
import com.redsun.hrm.entities.Information;


@Repository
public class InformationDaoImpl implements InformationDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	static private String SELECT_INFORMATION_BY_STAFF = "SELECT r.id as user_id, d.company_id"
											+ " FROM resources r inner join departments d on r.department_id = d.id"
											+ " WHERE r.code = ? AND r.client_id = ?";
	private static Logger log = Logger.getLogger(InformationDaoImpl.class);

	public List<Information> getInformation(String userName, int clientId) {
		// TODO Auto-generated method stub
		List<Information> inf = null;
		List<Object> params = new ArrayList<Object>();
		params.add(userName);
		params.add(clientId);
		try {
			inf = jdbcTemplate.query(SELECT_INFORMATION_BY_STAFF, params.toArray(), new InformationMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return inf;
	}
}
