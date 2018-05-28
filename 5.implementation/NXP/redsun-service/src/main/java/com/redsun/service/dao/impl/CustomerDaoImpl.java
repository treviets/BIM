package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.redsun.service.dao.CustomerDao;
import com.redsun.service.dao.mapper.CustomerMapper;
import com.redsun.service.entities.Customer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	static private String SELECT_CUSTOMER = "select id, no, name, address, phone, client_id, count(*) over() as total_count from customers where client_id = ?";
	private static Logger log = Logger.getLogger(CustomerDaoImpl.class);

	public List<Customer> listCustomers(int clientId, String code, String name, int itemsPerPage, int pageNo) {
		// TODO Auto-generated method stub
		List<Customer> listCustomer = null;
		int offset = (pageNo-1)*itemsPerPage;
		String sql = "select id, no, name, address, phone, client_id, count(*) over() as total_count"
				+ " from customers where client_id = ?";//
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		if(code.equals("undefined")==false){
			sql += " and UPPER(no) like UPPER(?)";
			params.add("%" + code + "%");
		}
		if(name.equals("undefined")==false){
			sql += " and UPPER(name) like UPPER(?)";
			params.add("%" + name + "%");
		}
		params.add(itemsPerPage);
		params.add(offset);
		
		sql = sql + " order by id ASC limit ? offset ?";
		try {
			listCustomer = jdbcTemplate.query(sql, params.toArray(), new CustomerMapper());
			return listCustomer;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listCustomer;
	}
	public List<Customer> getCustomerDetails(int clientId) {
		List<Customer> customers = null;
		try {
			customers = jdbcTemplate.query(SELECT_CUSTOMER, new Object[] { clientId }, new CustomerMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return customers;
	}


}
