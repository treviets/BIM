package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Criticality;

public interface CriticalityDao {
	// select
	List<Criticality> listCriticality(int itemsPerPage, int pageNo);

	// select
		List<Criticality> listAllCriticalities(int clientId);
		
	// insert
	int insert(Criticality criticality);

	// update
	int update(Criticality criticality);

	// delete
	int delete(Integer id);

}