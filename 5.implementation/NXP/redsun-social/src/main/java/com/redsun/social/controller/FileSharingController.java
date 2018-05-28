package com.redsun.social.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.social.entities.FileSharing;
import com.redsun.social.service.FileSharingService;

@RestController
@RequestMapping(value = "file-sharing")
public class FileSharingController {

	@Autowired
	FileSharingService fileSharingService;

	@RequestMapping(value = "create/", method = { RequestMethod.POST })
	public Object create(@RequestBody FileSharing fileSharing) throws IOException {

		Object result = fileSharingService.createFileSharing(fileSharing);

		return result;
	}

	@RequestMapping(value = "list-for-group/{projectId}/{groupId}/{clientId}/", method = { RequestMethod.GET })
	public Object getList(@PathVariable("projectId") Integer projectId, @PathVariable("groupId") Integer groupId,
			@PathVariable("clientId") Integer clientId) throws IOException {
		return fileSharingService.getFileSharingByGroup(projectId, groupId, clientId);
	}

	@RequestMapping(value = "delete/{fileSharingId}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("fileSharingId") Integer fileSharingId) throws IOException {
		Object result = fileSharingService.deleteFileSharing(fileSharingId);
		return result;
	}

	@RequestMapping(value = "getbyid/{fileId}", method = { RequestMethod.GET })
	public Object gteById(@PathVariable("fileId") Integer fileId) throws IOException {
		return fileSharingService.getById(fileId);
	}

}
