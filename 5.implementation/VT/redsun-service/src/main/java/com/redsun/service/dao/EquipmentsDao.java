package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Equipments;

public interface EquipmentsDao {

	// List for page
	List<Equipments> listEquipmentsForPage(final int clientId, final int itemsPerPage, final int pageNo);

	// select all
	List<Equipments> getAll(int clientId);

	// filter by equipments were exist in project_euqipments
	List<Equipments> filterEquipment(int projectId, int clientId);

	// insert
	int insert(Equipments equipment);

	// Count.
	long count(int clientId);

	// update
	int update(Equipments equipment);

	// delete
	int delete(int id);

	// get by id
	Equipments getById(int id, int clientId);

	// Get by code.
	List<Equipments> getByCode(String code, int clientId);

	// check exist
	List<Equipments> checkExist(int clientId, int id);

}