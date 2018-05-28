package com.redsun.hrm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.hrm.dao.StaffDao;
import com.redsun.hrm.dao.mapper.StaffMapper;
import com.redsun.hrm.entities.Staffs;


@Repository
public class StaffDaoImpl implements StaffDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	static private String SELECT_ALL_STAFFS = "SELECT id, code, name, full_name, department_id, position_id, email, mobile, description, img, client_id"
											+ " FROM resources WHERE client_id = ? AND is_trash = '0' AND is_contact = '0' ORDER BY code, name";
	private static Logger log = Logger.getLogger(StaffDaoImpl.class);

	public List<Staffs> getAllStaffs(int clientId) {
		// TODO Auto-generated method stub
		List<Staffs> listStaffs = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		try {
			listStaffs = jdbcTemplate.query(SELECT_ALL_STAFFS, params.toArray(), new StaffMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listStaffs;
	}
}
