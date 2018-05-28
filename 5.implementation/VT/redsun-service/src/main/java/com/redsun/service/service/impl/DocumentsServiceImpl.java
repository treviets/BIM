package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.DocumentVersionsDao;
import com.redsun.service.dao.DocumentsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.DocumentVersions;
import com.redsun.service.entities.Documents;
import com.redsun.service.service.DocumentsService;

/**
 * Documents Service implementation
 */
@Service
public class DocumentsServiceImpl implements DocumentsService {

	@Autowired
	private DocumentsDao documentsDao;
	@Autowired
	private DocumentVersionsDao documentVersionsDao;

	// Insert.
	@Override
	public Result insert(final Documents documents) {
		final int data = documentsDao.insert(documents);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("documents", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listDocumentsForOneProject(int clientId, int projectId) {
		// Call DAO.
		final List<Documents> data = documentsDao.listDocumentsOneProject(clientId, projectId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("documents", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getByDocumentId(int documentId) {

		final List<DocumentVersions> data = documentVersionsDao.getByDocumentId(documentId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("documentversions", data);
		// Return.
		return new Result(result, true);
	}
	@Override
	public Result getByTaskId(int taskId,int clientId) {

		final Documents data = documentsDao.getByTaskId(taskId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("documents", data);
		// Return.
		return new Result(result, true);
	}
}
