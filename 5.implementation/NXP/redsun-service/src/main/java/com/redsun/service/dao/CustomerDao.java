package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Customer;

public interface CustomerDao {
	 List<Customer> listCustomers(int clientId, String code, String name, int itemsPerPage, int pageNo);
	 List<Customer> getCustomerDetails(int clientId);
}
