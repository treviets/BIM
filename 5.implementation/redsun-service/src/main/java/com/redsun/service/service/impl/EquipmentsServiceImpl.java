package com.redsun.service.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.service.dao.EquipmentsDao;
import com.redsun.service.dao.ProjectEquipmentsDao;
import com.redsun.service.dao.UnitsDao;
import com.redsun.service.entities.Equipments;
import com.redsun.service.entities.ProjectEquipments;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Units;
import com.redsun.service.service.EquipmentsService;

@Service
public class EquipmentsServiceImpl implements EquipmentsService {

	@Autowired
	EquipmentsDao equipmentsDao;

	@Autowired
	private UnitsDao unitsDao;

	@Autowired
	private ProjectEquipmentsDao projectEquipmentsDao;

	@Override
	public Result getAll(int clientId) {
		final List<Equipments> data = equipmentsDao.getAll(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("equipments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result filterEquipment(int projectId, int clientId) {
		final List<Equipments> data = equipmentsDao.filterEquipment(projectId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("equipments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listEquipmentsForPage(int clientId, int itemsPerPage, int pageNo) {
		// Call DAO.
		final List<Equipments> data = equipmentsDao.listEquipmentsForPage(clientId, itemsPerPage, pageNo);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("equipments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(Equipments equipment) {
		// Call DAO.
		final int data = equipmentsDao.insert(equipment);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result count(int clientId) {
		// Call DAO.
		final long data = equipmentsDao.count(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result update(Equipments equipment) {
		// Call DAO.
		final int data = equipmentsDao.update(equipment);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getById(int id, int clientId) {
		// Call DAO.
		final Equipments data = equipmentsDao.getById(clientId, id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("equipments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(int id) {
		// Call DAO.
		final int data = equipmentsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result importEquipments(int projectId, int clientId, MultipartFile file) throws IOException, ParseException {

		// Import
		List<ProjectEquipments> projectEquipments = new ArrayList<ProjectEquipments>();
		List<String> rows = IOUtils.readLines(file.getInputStream(), StandardCharsets.UTF_16);
		int rowNum = rows.size();
		if (rowNum < 2) {
			rows = IOUtils.readLines(file.getInputStream(), StandardCharsets.UTF_8);
			rowNum = rows.size();
		}
		// Get column names.
		List<String> columnNames = new ArrayList<String>();
		String row = rows.get(0);
		String[] values = row.split("\t");
		int colNum = values.length;
		for (int i = 0; i < colNum; i++) {
			columnNames.add(values[i].trim().toLowerCase());
		}
		// Get column values.
		NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
		for (int i = 1; i < rows.size(); i++) {
			row = rows.get(i);
			// row = row.replaceAll("\"", "");
			values = row.split("\t", -1);
			if (values.length == 0) {
				continue;
			} else if (values.length < colNum) {
				final Map<String, Object> result = new HashMap<String, Object>();
				result.put("count", i - 1);
				// Return.
				return new Result(result, false);
			}
			String equipmentCode = values[0].trim();
			int equipmentId = -1;
			// Check equipment by code in the database.
			List<Equipments> equipmentsDB = equipmentsDao.getByCode(equipmentCode, clientId);
			Equipments equipment = null;
			if (null == equipmentsDB || equipmentsDB.size() < 1) {
				// Insert equipment.
				equipment = new Equipments();
				equipment.setCode(values[columnNames.indexOf("code")].trim());
				equipment.setName(values[columnNames.indexOf("name")].trim());
				equipment.setDescription(values[columnNames.indexOf("description")].trim());
				if (!values[columnNames.indexOf("quantity")].trim().equals("")) {
					equipment.setQuantity(Integer.parseInt(values[columnNames.indexOf("quantity")].trim()));
				}
				if (!values[columnNames.indexOf("netprice")].trim().equals("")) {
					equipment.setNetPrice(
							numberFormat.parse(values[columnNames.indexOf("netprice")].trim()).doubleValue());
				}
				if (!values[columnNames.indexOf("price")].trim().equals("")) {
					equipment.setPrice(numberFormat.parse(values[columnNames.indexOf("price")].trim()).doubleValue());
				}
				if (!values[columnNames.indexOf("unitname")].trim().equals("")) {
					List<Units> unitses = unitsDao.getByName(clientId, values[columnNames.indexOf("unitname")].trim());
					if (unitses.size() != 1) {
						final Map<String, Object> result = new HashMap<String, Object>();
						result.put("count", i - 1);
						// Return.
						return new Result(result, false);
					}
					equipment.setUnit(unitses.get(0).getId());
				}
				equipment.setClientId(clientId);
				equipmentId = equipmentsDao.insert(equipment);
			} else {
				// Update equipment.
				equipment = equipmentsDB.get(0);
				equipment.setName(values[columnNames.indexOf("name")].trim());
				equipment.setDescription(values[columnNames.indexOf("description")].trim());
				if (!values[columnNames.indexOf("quantity")].trim().equals("")) {
					equipment.setQuantity(Integer.parseInt(values[columnNames.indexOf("quantity")].trim()));
				}
				if (!values[columnNames.indexOf("netprice")].trim().equals("")) {
					equipment.setNetPrice(
							numberFormat.parse(values[columnNames.indexOf("netprice")].trim()).doubleValue());
				}
				if (!values[columnNames.indexOf("price")].trim().equals("")) {
					equipment.setPrice(numberFormat.parse(values[columnNames.indexOf("price")].trim()).doubleValue());
				}
				if (!values[columnNames.indexOf("unitname")].trim().equals("")) {
					List<Units> unitses = unitsDao.getByName(clientId, values[columnNames.indexOf("unitname")].trim());
					if (unitses.size() != 1) {
						final Map<String, Object> result = new HashMap<String, Object>();
						result.put("count", i - 1);
						// Return.
						return new Result(result, false);
					}
					equipment.setUnit(unitses.get(0).getId());
				}
				equipmentsDao.update(equipment);
				equipmentId = equipment.getId();
			}

			// Check project equipment by id in the database.
			List<ProjectEquipments> projectEquipmentsDB = projectEquipmentsDao.getByProjectIdAndEquipmentId(projectId,
					equipmentId, clientId);
			ProjectEquipments projectEquipment = null;
			if (null == projectEquipmentsDB || projectEquipmentsDB.size() < 1) {
				// Insert into ProjectEquipment.
				projectEquipment = new ProjectEquipments();
				projectEquipment.setProjectId(projectId);
				projectEquipment.setEquipmentId(equipmentId);
				projectEquipment.setEquipmentName(equipment.getName());
				projectEquipment.setQuantity(equipment.getQuantity());
				projectEquipment.setNetPrice(equipment.getNetPrice());
				projectEquipment.setPrice(equipment.getPrice());
				projectEquipment.setClientId(clientId);

				projectEquipments.add(projectEquipment);
			} else {
				// Update ProjectEquipment.
				projectEquipment = projectEquipmentsDB.get(0);
				projectEquipment.setEquipmentName(equipment.getName());
				projectEquipment.setQuantity(equipment.getQuantity());
				projectEquipment.setNetPrice(equipment.getNetPrice());
				projectEquipment.setPrice(equipment.getPrice());

				projectEquipmentsDao.update(projectEquipment);
			}
		}
		if (projectEquipments.size() > 0) {
			projectEquipmentsDao.insert(projectEquipments);
		}

		// Return result.
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", projectEquipments.size());
		return new Result(result, true);
	}

	@Override
	public Result checkExist(int clientId, int id) {
		// Call DAO.
		final List<Equipments> data = equipmentsDao.checkExist(clientId, id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("equipmentexist", data);
		// Return.
		return new Result(result, true);
	}

}
