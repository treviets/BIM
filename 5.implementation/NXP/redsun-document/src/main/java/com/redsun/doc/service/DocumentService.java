package com.redsun.doc.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.redsun.doc.entities.Document;
import com.redsun.doc.entities.Result;

public interface DocumentService{
	
    // Insert.
	Result insert(final Document document) throws IOException;

    // Update.
	Result update(final Document document) throws IOException;

    // Delete.
	Result delete(final Integer id, final String location, final String fileName) throws IOException;

	// Get By Id.
	Result getById(final Integer id);

    // Exists.
	Result exists(final Integer id);

    // Count.
	Result count() ;
	Result upload(final String location, final MultipartFile file) throws IOException;
}