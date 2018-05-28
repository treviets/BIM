package com.redsun.service.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.DocumentVersionsDao;
import com.redsun.service.entities.DocumentVersions;
import com.redsun.service.entities.Result;
import com.redsun.service.service.DocumentVersionsService;
import com.redsun.service.utils.RedSunConstants;

/**
 * Documents Service implementation
 */
@Service
public class DocumentVersionsServiceImpl implements DocumentVersionsService {

	@Autowired
	private DocumentVersionsDao documentVersionsDao;

	@Override
	public Result getByDocumentId(int documentId) {

		final List<DocumentVersions> data = documentVersionsDao.getByDocumentId(documentId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("documentversions", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getById(int id) {
		final DocumentVersions data = documentVersionsDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("documentversions", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(DocumentVersions documentVersion) {
		final int data = documentVersionsDao.insert(documentVersion);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("documentversions", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listOnPage(int clientId, int projectId) {
		final List<DocumentVersions> data = documentVersionsDao.listOnPage(clientId, projectId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("documentversions", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getByTaskId(int taskId) {

		final List<DocumentVersions> data = documentVersionsDao.getByTaskId(taskId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("documentversions", data);
		// Return.
		return new Result(result, true);
	}
	
	@Override
	public Result download(final Integer id) {
		final DocumentVersions docs = documentVersionsDao.getById(id);
		final Map<String, Object> result = new HashMap<>();
		if(docs != null){
			final String pathToFile = RedSunConstants.rootDirectory.replace('\\', File.separatorChar) + docs.getLink();
			final File file =  new File(pathToFile);
			result.put("file", file);
			//result.put("fileType", new File(attach.getMimeType()));
		}
		return new Result(result, true);
	}
	
	// Delete.
	@Override
	public Result deleteDocVersion(final Integer documentId) throws IOException {
		// Call DAO.
		DocumentVersions doc = documentVersionsDao.getById(documentId);
		if(doc == null) {
			Result result = new Result();
			//result.setStatus();// Not exist.
			result.setDescription("NotExist");// Code for not exist (language).
			return result; 
		}
		
		final int data = documentVersionsDao.delete(documentId);
		final String pathToFile = RedSunConstants.rootDirectory.replace('\\', File.separatorChar) + doc.getLink();
		final Path path = Paths.get(pathToFile);
		if (Files.exists(path)) {
			FileUtils.forceDelete(new File(path.toUri()));
		}
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		return new Result(result, true);
	}
}
