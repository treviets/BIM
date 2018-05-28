package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.DocumentVersions;

/**
 * Documents DAO interface
 */
public interface DocumentVersionsDao {

	// get by projectId
	List<DocumentVersions> listOnPage(int clientId, int projectId);

	// Get By documentId.
	List<DocumentVersions> getByDocumentId(final int documentId);

	// Get By taskId.
	List<DocumentVersions> getByTaskId(final int taskId);

	// get by id
	DocumentVersions getById(final int id);

	// update
	int insert(DocumentVersions documentVersion);
	
	int delete(final Integer id);
}
