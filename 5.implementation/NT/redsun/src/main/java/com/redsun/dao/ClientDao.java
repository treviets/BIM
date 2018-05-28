package com.redsun.dao;

import com.redsun.entities.Client;

public interface ClientDao {
	public Client getClientById(int id) throws Exception;
}
