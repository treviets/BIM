package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Materials;

public interface MaterialsDao {
	// select all
	List<Materials> getAll(int clientId);

	// filter by materials were exist in project_materials
	List<Materials> filterMaterial(int projectId, int clientId);

	// List for page
	List<Materials> listMaterialsForPage(final int clientId, final int itemsPerPage, final int pageNo);

	// insert
	int insert(Materials material);

	// Count.
	long count(int clientId);

	// update
	int update(Materials material);

	// delete
	int delete(int id);

	// get by id
	Materials getById(int id, int clientId);

	// Get by code.
	List<Materials> getByCode(String code, int clientId);

	// check exist
	List<Materials> checkExist(int clientId, int id);
}