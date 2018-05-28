package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.Documents;
import com.redsun.service.service.DocumentsService;

/**
 * Documents Controller
 */
@RestController
@RequestMapping("documentsservice")
public class DocumentsController {

	// Service.
	@Autowired
	DocumentsService documentsService;

	// Insert.
	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Documents documents) {
		return documentsService.insert(documents);
	}

	// List documents for one the project
	@RequestMapping(value = "list/{clientId}/{projectId}", method = { RequestMethod.GET })
	public Object listDocuments(@PathVariable("clientId") final int clientId,
			@PathVariable("projectId") int projectId) {
		return documentsService.listDocumentsForOneProject(clientId, projectId);
	}

	// get id document to upload file in edit task
	@RequestMapping(value = "getbytaskid/{taskId}/{clientId}", method = { RequestMethod.GET })
	public Object getByTaskId(@PathVariable("taskId") final Integer taskId,
			@PathVariable("clientId") Integer clientId) {
		return documentsService.getByTaskId(taskId, clientId);
	}

}
