package com.redsun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.dao.ClientDao;
import com.redsun.dao.UserDao;
import com.redsun.entities.Client;
import com.redsun.entities.User;
import com.redsun.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	UserDao userDao;
	@Autowired
	ClientDao clientDao;
	
	public User getClient(String username) throws Exception {
		
		return userDao.getUserByUsername(username);
	}

	public Client getClientById(int id) throws Exception {
		return clientDao.getClientById(id);
	}
}
