package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Risk;

public interface RiskDao {
	// select
	List<Risk> listRisk(int clientId, int itemsPerPage, int pageNo);

	// list risks for one project
	List<Risk> listRiskOneProject(int clientId, int projectId);

	// insert
	int insert(Risk risk);

	// update
	int update(Risk risk);

	// delete
	int delete(Integer id);

	// getById
	Risk getById(Integer id);

}
