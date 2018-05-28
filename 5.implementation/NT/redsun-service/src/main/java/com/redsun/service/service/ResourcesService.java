package com.redsun.service.service;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.web.multipart.MultipartFile;

import com.redsun.service.entities.Resources;
import com.redsun.service.entities.Result;

/**
 * Resources Service interface
 */
public interface ResourcesService {

	// Insert.
	Result insert(final Resources resources);

	// Update.
	Result update(final Resources resources);

	// Delete.
	Result delete(final Integer id);
	
	// Delete.
	Result restore(final Integer id, final Integer clientId);

	// Get By Id.
	Result getById(int clientId, int id);

	// Exists.
	Result exists(final Integer id);

	// Count.
	Result count(int clientId);

	// List for page and filter.
	Result listResourcesForPageAndFilter(final int clientId, final int itemsPerPage, final int pageNo);

	// get All
	Result listAll(final Integer clientId, Integer... deletedFlag);
	
	// get All
	Result listAllForAllType(final Integer clientId, Integer... deletedFlag);

	// filter by members were exists in affectations

	Result filterMember(int projectId, int clientId);

	// List for Exterior
	Result listResourcesExterior(final int clientId, final int itemsPerPage, final int pageNo);

	// // Import material by file.
	Result importResources(int projectId, int clientId, MultipartFile file) throws IOException, ParseException;

	// filter worker
	Result filterTitle(int clientId);

	// get all to check exist

	Result getToExist(int clientId, int resourceId);

	// get resource for bpmn
	Result getForBPMN(String code);
}
