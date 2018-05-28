package com.redsun.service.service;

import com.redsun.service.entities.Result;

public interface CustomerService {
	 Object getCustomers(int clientId, String code, String name, int itemsPerPage, int pageNo);
	 Result getAllCustomers(int clientId);
}
