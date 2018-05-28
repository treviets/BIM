package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Priority;

public interface PriorityDao {

	// select
	List<Priority> listPriority(int client_id, String name, int itemsPerPage, int pageNo);


	// select
	List<Priority> listAllPriories(int clientId);

	// insert
	int insert(Priority priority);

	// update
	int update(Priority priority);

	// delete
	int delete(Integer id);

	// getById
	Priority getById(Integer id);

}
