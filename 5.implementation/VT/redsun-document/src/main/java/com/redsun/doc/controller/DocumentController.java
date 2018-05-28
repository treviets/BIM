package com.redsun.doc.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.doc.entities.Document;
import com.redsun.doc.service.DocumentService;

@RestController
@RequestMapping(value = "document")
public class DocumentController { 
	
	
	@Autowired
    DocumentService documentService;

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Document document) throws IOException {
		return documentService.insert(document);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody Document document) throws IOException {
		return documentService.update(document);
	}
	
	// Delete.
	@RequestMapping(value = "delete", method = { RequestMethod.POST })
	public Object delete(@Validated @RequestBody Document document) throws IOException {
		int id = document.getId();
		String location = document.getLocation();
		String fileName = document.getName();
		return documentService.delete(id, location, fileName);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return documentService.getById(id);
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
    public Object upload(@RequestParam("location") String location, @RequestParam("files") MultipartFile files) throws IOException {
        return documentService.upload(location, files);
    }

}
