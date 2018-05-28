package com.redsun.service.service;

import com.redsun.service.entities.Result;
import com.redsun.service.entities.Risk;

public interface RiskService {

	// select risks
	Result getRisks(int clientId, int itemsPerPage, int pageNo);

	// list risks for one project
	Result getListRiskOneProject(int clientId, int projectId);

	// insert
	Result insert(Risk risk);

	// update
	Result update(Risk risk);

	// delete
	Result delete(Integer id);

	// get by id
	Result getById(Integer id);
}
