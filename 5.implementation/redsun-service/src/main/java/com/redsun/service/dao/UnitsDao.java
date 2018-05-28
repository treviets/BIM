package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Units;

public interface UnitsDao {
	
	// Get by name
	List<Units> getByName(final int clientId, final String name);
	
	// select all
	List<Units> listAllUnits(int clientId);
}