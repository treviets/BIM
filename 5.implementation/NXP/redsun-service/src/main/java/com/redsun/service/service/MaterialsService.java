package com.redsun.service.service;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.web.multipart.MultipartFile;

import com.redsun.service.entities.Materials;
import com.redsun.service.entities.Result;

public interface MaterialsService {

	// insert
	Result getAll(int clientId);

	// filter by materials were exists in project_materials
	Result filterMaterial(int projectId, int clientId);

	// list for page
	Result listMaterialsForPage(int clientId, int itemsPerPage, int pageNo);

	// insert
	Result insert(Materials material);

	// Count.
	Result count(int clientId);

	// Update.
	Result update(Materials material);

	// delete
	Result delete(int id);

	// Get By Id.
	Result getById(int id, int clientId);

	// Check exist
	Result checkExist(int clientId, int id);

	// Import material by file.
	Result importMaterials(int projectId, int clientId, MultipartFile file) throws IOException, ParseException;
}
