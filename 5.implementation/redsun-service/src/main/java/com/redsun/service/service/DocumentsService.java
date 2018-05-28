package com.redsun.service.service;

import com.redsun.service.entities.Documents;
import com.redsun.service.entities.Result;

/**
 * Documents Service interface
 */
public interface DocumentsService {

	// Insert.
	Result insert(final Documents documents);

	// List documents for one the project
	Result listDocumentsForOneProject(final int clientId, final int projectId);

	// Get By DocumentId.
	Result getByDocumentId(final int documentId);

	// get By taskId
	Result getByTaskId(int taskId, int clientId);
}
