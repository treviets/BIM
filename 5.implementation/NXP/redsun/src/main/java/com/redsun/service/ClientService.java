package com.redsun.service;

import com.redsun.entities.Client;
import com.redsun.entities.User;

public interface ClientService {
	public User getClient(String username) throws Exception;
	public Client getClientById(int id) throws Exception;
}
