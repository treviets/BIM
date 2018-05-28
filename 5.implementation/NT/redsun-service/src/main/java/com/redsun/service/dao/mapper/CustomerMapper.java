package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.service.entities.Customer;

public class CustomerMapper implements RowMapper<Customer> {

	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

		Customer customer = null;
		customer = new Customer();
		customer.setId(rs.getInt("id"));
		customer.setNo(rs.getString("no"));
		customer.setName(rs.getString("name"));
		customer.setAddress(rs.getString("address"));
		customer.setPhone(rs.getString("phone"));
		customer.setClientId(rs.getInt("client_id"));
		customer.setTotalCount(rs.getInt("total_count"));
		return customer;
	}

}
