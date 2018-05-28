package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Documents;

/**
 * Documents DAO interface
 */
public interface DocumentsDao {

	// insert.
	int insert(final Documents documents);

	// List documents for one the project
	List<Documents> listDocumentsOneProject(final int clientId, final int projectId);

	// get id document by taskId, to update file in task
	Documents getByTaskId(int taskId, int clientId);
}
