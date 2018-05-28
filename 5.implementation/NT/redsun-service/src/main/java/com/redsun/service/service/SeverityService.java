package com.redsun.service.service;

import com.redsun.service.entities.Severity;

public interface SeverityService {

	// select
	Object getSeverity(int itemsPerPage, int pageNo);

	// select all severities
	Object listAllSeverities(int clientId);

	// insert
	Object insert(Severity severity);

	// update
	Object update(Severity severity);

	// delete
	Object delete(Integer id);

}
