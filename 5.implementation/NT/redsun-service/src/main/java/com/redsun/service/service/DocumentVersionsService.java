package com.redsun.service.service;

import java.io.IOException;

import com.redsun.service.entities.DocumentVersions;
import com.redsun.service.entities.Result;

/**
 * Documents Service interface
 */
public interface DocumentVersionsService {

	// get by projectId
	Result listOnPage(int clientId, int projectId);

	// Get By DocumentId.
	Result getByDocumentId(final int documentId);

	// get By taskId
	Result getByTaskId(final int taskId);

	// get by id
	Result getById(final int id);

	// update
	Result insert(DocumentVersions documentVersion);
	
	Result download(final Integer id);
	
	 Result deleteDocVersion(final Integer documentId) throws IOException;
}
