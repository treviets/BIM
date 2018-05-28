package com.redsun.service.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.DocumentVersions;
import com.redsun.service.service.DocumentVersionsService;

/**
 * Documents Controller
 */
@RestController
@RequestMapping("documentversionsservice")
public class DocumentVersionsController {

	// Service.
	@Autowired
	DocumentVersionsService documentVersionsService;

	// Get by projectId
	@RequestMapping(value = "list/{clientId}/{projectId}", method = { RequestMethod.GET })
	public Object getListAll(@PathVariable("clientId") final Integer clientId,
			@PathVariable("projectId") final Integer projectId) {
		return documentVersionsService.listOnPage(clientId, projectId);
	}

	// Get by documentId
	@RequestMapping(value = "getbydocumentid/{documentId}", method = { RequestMethod.GET })
	public Object getByDocumentId(@PathVariable("documentId") final Integer documentId) {
		return documentVersionsService.getByDocumentId(documentId);
	}

	// Get by taskId
	@RequestMapping(value = "getbytaskid/{taskId}", method = { RequestMethod.GET })
	public Object getByTaskId(@PathVariable("taskId") final Integer taskId) {
		return documentVersionsService.getByTaskId(taskId);
	}

	// Get by id
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") final Integer id) {
		return documentVersionsService.getById(id);
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object addDocumentVersion(@RequestBody DocumentVersions documentVersion) throws Exception {
		return documentVersionsService.insert(documentVersion);
	}
	
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public Resource download(@PathVariable final Integer id, final HttpServletResponse response) throws IOException {
        final Map<String, Object> result = documentVersionsService.download(id).getResult();
        final File file = (File) result.get("file");
        //final String fileType = result.get("fileType").toString().replace('\\','/');
        if (file != null) {
            //response.setContentType(fileType);
            response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
            response.setHeader("Content-Length", String.valueOf(file.length()));
            return new FileSystemResource(file);
        }
        return null;
    }
	// Delete.
	@RequestMapping(value = "delete", method = { RequestMethod.POST })
	public Object delete(@Validated @RequestBody DocumentVersions document) throws IOException {
		int id = document.getId();
		return documentVersionsService.deleteDocVersion(id);
	}
}
