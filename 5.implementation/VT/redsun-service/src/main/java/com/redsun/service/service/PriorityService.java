package com.redsun.service.service;

import com.redsun.service.entities.Priority;
import com.redsun.service.entities.Result;

public interface PriorityService {

	//select on page
	Result getPriority(int client_id, String name, int itemsPerPage, int pageNo);

	//select all priorities
	Result listAllPriorities(int clientId);

	//insert
	Result insert(Priority priority);

	//update
	Result update(Priority priority);

	//delete
	Result delete(Integer id);

	//get by id
	Result getById(Integer id);

}
