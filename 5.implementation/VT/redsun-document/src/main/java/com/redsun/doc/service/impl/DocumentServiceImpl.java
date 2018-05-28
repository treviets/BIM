package com.redsun.doc.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.redsun.doc.dao.DirectoryDao;
import com.redsun.doc.dao.DocumentDao;
import com.redsun.doc.entities.Document;
import com.redsun.doc.entities.Result;
import com.redsun.doc.service.DocumentService;


@Service
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	DirectoryDao directoryDao;
	@Autowired
	DocumentDao documentDao;
    @Autowired
	private Environment env;
	
    // Insert.
	@Override
	public Result insert(final Document document) throws IOException {
		// Call DAO.
		Date currentDate = new Date();
		document.setCreateDateTime(currentDate);
		//document.setCreateUserName(createUserName);
		List<Document> doubleFiles = directoryDao.listFiles(document.getDirectoryId(), document.getName());
		for(int i=0; i<doubleFiles.size(); i++)
			documentDao.delete(doubleFiles.get(i).getId());
		final int documentId = documentDao.insert(document);
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", documentId);
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Document document) throws IOException {
		// Call DAO.
		//Date currentDate = new Date();
		//document.setCreateUserName(createUserName);
		final List<Document> directoriesDB = documentDao.getById(document.getId());
		if(directoriesDB.isEmpty()) {
			Result result = new Result();
			//result.setStatus();// Not exist.
			result.setDescription("NotExist");// Code for not exist (language).
			return result; 
		}
		
		final int documentId = documentDao.update(document);
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", documentId);
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer documentId, final String location, final String fileName) throws IOException {
		// Call DAO.
		final List<Document> directoriesDB = documentDao.getById(documentId);
		if(directoriesDB.isEmpty()) {
			Result result = new Result();
			//result.setStatus();// Not exist.
			result.setDescription("NotExist");// Code for not exist (language).
			return result; 
		}
		
		final int data = documentDao.delete(documentId);
		final String pathToFile = env.getProperty("rootDirectory").replace('\\', File.separatorChar) + location.replace('\\', File.separatorChar)+ fileName;
		final Path path = Paths.get(pathToFile);
		if (Files.exists(path)) {
			FileUtils.forceDelete(new File(path.toUri()));
		}
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer documentId) {
		// Call DAO.
		final List<Document> data = documentDao.getById(documentId);
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("document", data);
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer documentId) {
		// Call DAO.
		final boolean data = documentDao.exists(documentId);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = documentDao.count();

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		return new Result(result, true);
	}
	
	@Override
	@Transactional
	public Result upload(final String location, final MultipartFile file) throws IOException {
		 final String absolutePath = env.getProperty("rootDirectory") + location.replace('/', File.separatorChar);
		final File dir = new File(absolutePath);
		if (!dir.exists())
			dir.mkdirs();
		final String fileName = absolutePath + file.getOriginalFilename().split("%&%&%")[0];
		// TODO check same file name before create new file
		final Path path = Files.createFile(Paths.get(fileName));
		file.transferTo(new File(path.toUri()));
		return new Result(new HashMap<String, Object>(), true);
	}

}