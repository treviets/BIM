package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.CustomerDao;
import com.redsun.service.entities.Customer;
import com.redsun.service.entities.Result;
import com.redsun.service.service.CustomerService;
import com.redsun.service.utils.RedSunConstants;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;
	Result result = new Result();
	public Object getCustomers(int clientId, String code, String name, int itemsPerPage, int pageNo) {
		// TODO Auto-generated method stub
		result.setStatus(RedSunConstants.STATUS_SUCCESS_FLAG);
		result.setDescription(RedSunConstants.STATUS_SUCCESS_STRING);
		Map<String, List<Customer>> customers = new HashMap<String, List<Customer>>();
		customers.put("customers", customerDao.listCustomers(clientId, code, name, itemsPerPage, pageNo));
		result.setResult(customers);
		return result;
	}
	@Override
	public Result getAllCustomers(int clientId) {
		final List<Customer> data = customerDao.getCustomerDetails(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("customers", data);
		// Return.
		return new Result(result, true);
	}

}
