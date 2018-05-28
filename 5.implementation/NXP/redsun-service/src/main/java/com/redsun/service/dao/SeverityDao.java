package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Severity;

public interface SeverityDao {
	// select
	List<Severity> listSeverity(int itemsPerPage, int pageNo);

	// select
	List<Severity> listAllSeverities(int clientId);

	// insert
	int insert(Severity severity);

	// update
	int update(Severity severity);

	// delete
	int delete(Integer id);

}