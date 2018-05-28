package com.redsun.service.service;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.web.multipart.MultipartFile;

import com.redsun.service.entities.Equipments;
import com.redsun.service.entities.Result;

public interface EquipmentsService {

	// get all
	Result getAll(int clientId);

	// filter by equipments were exists in project_equipments
	Result filterEquipment(int projectId, int clientId);

	// list for paging
	Result listEquipmentsForPage(int clientId, int itemsPerPage, int pageNo);

	// insert
	Result insert(Equipments equipment);

	// Count.
	Result count(int clientId);

	// Update.
	Result update(Equipments equipment);

	// delete
	Result delete(int id);
	
	// Check exist
	Result checkExist(int clientId, int id);

	// Get By Id.
	Result getById(int clientId, int id);

	// Import equipment by file.
	Result importEquipments(int projectId, int clientId, MultipartFile file) throws IOException, ParseException;
}
