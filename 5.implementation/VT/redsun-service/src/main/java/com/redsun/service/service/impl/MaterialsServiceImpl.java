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

import com.redsun.service.dao.MaterialsDao;
import com.redsun.service.dao.ProjectMaterialsDao;
import com.redsun.service.dao.UnitsDao;
import com.redsun.service.entities.Materials;
import com.redsun.service.entities.ProjectMaterials;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Units;
import com.redsun.service.service.MaterialsService;

@Service
public class MaterialsServiceImpl implements MaterialsService {

	@Autowired
	MaterialsDao materialsDao;

	@Autowired
	private UnitsDao unitsDao;

	@Autowired
	private ProjectMaterialsDao projectMaterialsDao;

	@Override
	public Result getAll(int clientId) {
		final List<Materials> data = materialsDao.getAll(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("materials", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result filterMaterial(int projectId, int clientId) {
		final List<Materials> data = materialsDao.filterMaterial(projectId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("materials", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listMaterialsForPage(int clientId, int itemsPerPage, int pageNo) {
		// Call DAO.
		final List<Materials> data = materialsDao.listMaterialsForPage(clientId, itemsPerPage, pageNo);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("materials", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(Materials material) {
		// Call DAO.
		final int data = materialsDao.insert(material);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result count(int clientId) {
		// Call DAO.
		final long data = materialsDao.count(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result update(Materials material) {
		// Call DAO.
		final int data = materialsDao.update(material);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(int id) {
		// Call DAO.
		final int data = materialsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getById(int id, int clientId) {
		// Call DAO.
		final Materials data = materialsDao.getById(clientId, id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("materials", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result importMaterials(int projectId, int clientId, MultipartFile file) throws IOException, ParseException {

		// Import
		List<ProjectMaterials> projectMaterials = new ArrayList<ProjectMaterials>();
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
			String materialCode = values[0].trim();
			int materialId = -1;
			// Check material by code in the database.
			List<Materials> materialsDB = materialsDao.getByCode(materialCode, clientId);
			Materials material = null;
			if (null == materialsDB || materialsDB.size() < 1) {
				// Insert material.
				material = new Materials();
				material.setCode(values[columnNames.indexOf("code")].trim());
				material.setName(values[columnNames.indexOf("name")].trim());
				material.setDescription(values[columnNames.indexOf("description")].trim());
				if (!values[columnNames.indexOf("quantity")].trim().equals("")) {
					material.setQuantity(
							numberFormat.parse(values[columnNames.indexOf("quantity")].trim()).doubleValue());
				}
				if (!values[columnNames.indexOf("netprice")].trim().equals("")) {
					material.setNetPrice(
							numberFormat.parse(values[columnNames.indexOf("netprice")].trim()).doubleValue());
				}
				if (!values[columnNames.indexOf("price")].trim().equals("")) {
					material.setPrice(numberFormat.parse(values[columnNames.indexOf("price")].trim()).doubleValue());
				}
				if (!values[columnNames.indexOf("unitname")].trim().equals("")) {
					List<Units> unitses = unitsDao.getByName(clientId, values[columnNames.indexOf("unitname")].trim());
					if (unitses.size() != 1) {
						final Map<String, Object> result = new HashMap<String, Object>();
						result.put("count", i - 1);
						// Return.
						return new Result(result, false);
					}
					material.setUnit(unitses.get(0).getId());
				}
				material.setClientId(clientId);
				materialId = materialsDao.insert(material);
			} else {
				// Update material.
				material = materialsDB.get(0);
				material.setName(values[columnNames.indexOf("name")].trim());
				material.setDescription(values[columnNames.indexOf("description")].trim());
				if (!values[columnNames.indexOf("quantity")].trim().equals("")) {
					material.setQuantity(
							numberFormat.parse(values[columnNames.indexOf("quantity")].trim()).doubleValue());
				}
				if (!values[columnNames.indexOf("netprice")].trim().equals("")) {
					material.setNetPrice(
							numberFormat.parse(values[columnNames.indexOf("netprice")].trim()).doubleValue());
				}
				if (!values[columnNames.indexOf("price")].trim().equals("")) {
					material.setPrice(numberFormat.parse(values[columnNames.indexOf("price")].trim()).doubleValue());
				}
				if (!values[columnNames.indexOf("unitname")].trim().equals("")) {
					List<Units> unitses = unitsDao.getByName(clientId, values[columnNames.indexOf("unitname")].trim());
					if (unitses.size() != 1) {
						final Map<String, Object> result = new HashMap<String, Object>();
						result.put("count", i - 1);
						// Return.
						return new Result(result, false);
					}
					material.setUnit(unitses.get(0).getId());
				}
				materialsDao.update(material);
				materialId = material.getId();
			}

			// Check project material by id in the database.
			List<ProjectMaterials> projectMaterialsDB = projectMaterialsDao.getByProjectIdAndMaterialId(projectId,
					materialId, clientId);
			ProjectMaterials projectMaterial = null;
			if (null == projectMaterialsDB || projectMaterialsDB.size() < 1) {
				// Insert into ProjectMaterial.
				projectMaterial = new ProjectMaterials();
				projectMaterial.setProjectId(projectId);
				projectMaterial.setMaterialId(materialId);
				projectMaterial.setMaterialName(material.getName());
				projectMaterial.setQuantity(material.getQuantity());
				projectMaterial.setNetPrice(material.getNetPrice());
				projectMaterial.setPrice(material.getPrice());
				projectMaterial.setClientId(clientId);

				projectMaterials.add(projectMaterial);
			} else {
				// Update ProjectMaterial.
				projectMaterial = projectMaterialsDB.get(0);
				projectMaterial.setMaterialName(material.getName());
				projectMaterial.setQuantity(material.getQuantity());
				projectMaterial.setNetPrice(material.getNetPrice());
				projectMaterial.setPrice(material.getPrice());

				projectMaterialsDao.update(projectMaterial);
			}
		}
		if (projectMaterials.size() > 0) {
			projectMaterialsDao.insert(projectMaterials);
		}

		// Return result.
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", projectMaterials.size());
		return new Result(result, true);
	}

	@Override
	public Result checkExist(int clientId, int id) {
		// Call DAO.
		final List<Materials> data = materialsDao.checkExist(clientId, id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("materialexist", data);
		// Return.
		return new Result(result, true);
	}

}
