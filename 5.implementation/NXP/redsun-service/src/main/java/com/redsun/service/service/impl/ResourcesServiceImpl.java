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

import com.redsun.service.dao.ProjectResourcesDao;
import com.redsun.service.dao.ResourcesDao;
import com.redsun.service.entities.ProjectResources;
import com.redsun.service.entities.Resources;
import com.redsun.service.entities.Result;
import com.redsun.service.service.ResourcesService;

/**
 * Resources Service implementation
 */
@Service
public class ResourcesServiceImpl implements ResourcesService {

	@Autowired
	private ResourcesDao resourcesDao;

	@Autowired
	private ProjectResourcesDao projectResourcesDao;

	// Insert.
	@Override
	public Result insert(final Resources resources) {
		// Call DAO.
		final int data = resourcesDao.insert(resources);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Update.
	@Override
	public Result update(final Resources resources) {
		// Call DAO.
		final int data = resourcesDao.update(resources);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = resourcesDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result restore(final Integer resourceId, final Integer clientId) {
		// Call DAO.
		final int data = resourcesDao.restore(resourceId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}
	
	// Get By Id.
	@Override
	public Result getById(int clientId, int id) {
		// Call DAO.
		final Resources data = resourcesDao.getById(clientId, id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("resources", data);
		// Return.
		return new Result(result, true);
	}

	// Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = resourcesDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

	// Count
	@Override
	public Result count(int clientId) {
		// Call DAO.
		final long data = resourcesDao.count(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listResourcesForPageAndFilter(final int clientId, final int itemsPerPage, final int pageNo) {
		// Call DAO.
		final List<Resources> data = resourcesDao.listResourcesForPageAndFilter(clientId, itemsPerPage, pageNo);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("resources", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listAll(Integer clientId, Integer... deletedFlag) {
		final List<Resources> data = resourcesDao.getAll(clientId, deletedFlag);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("resources", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listAllForAllType(Integer clientId, Integer... deletedFlag) {
		final List<Resources> data = resourcesDao.getAllForAllType(clientId, deletedFlag);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("resources", data);
		// Return.
		return new Result(result, true);
	}
	
	@Override
	public Result filterMember(int projectId, int clientId) {
		final List<Resources> data = resourcesDao.filterMember(projectId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("resources", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listResourcesExterior(int clientId, int itemsPerPage, int pageNo) {
		final List<Resources> data = resourcesDao.listResourcesExterior(clientId, itemsPerPage, pageNo);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("resources", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result importResources(int projectId, int clientId, MultipartFile file) throws IOException, ParseException {

		// Import
		List<ProjectResources> projectResources = new ArrayList<ProjectResources>();
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
		for (int i = 1; i < rowNum; i++) {
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
			String resourceCode = values[columnNames.indexOf("code")].trim();
			int resourceId = -1;
			// Check resource by code in the database.
			List<Resources> resourcesDB = resourcesDao.getByCode(resourceCode, clientId);
			Resources resource = null;
			if (null == resourcesDB || resourcesDB.size() < 1) {
				// Insert resource.
				resource = new Resources();
				resource.setCode(values[columnNames.indexOf("code")].trim());
				resource.setTitle(values[columnNames.indexOf("title")].trim());
				resource.setName(values[columnNames.indexOf("name")].trim());
				resource.setFullName(values[columnNames.indexOf("fullname")].trim());
				resource.setIdCard(values[columnNames.indexOf("id")].trim());
				resource.setMobile(values[columnNames.indexOf("mobile")].trim());
				resource.setEmail(values[columnNames.indexOf("email")].trim());
				resource.setAddress(values[columnNames.indexOf("address")].trim());
				if (!values[columnNames.indexOf("salary")].trim().equals("")) {
					resource.setSalary(numberFormat.parse(values[columnNames.indexOf("salary")].trim()).doubleValue());
				}
				resource.setClientId(clientId);
				resourceId = resourcesDao.insert(resource);
			} else {
				// Update resource
				resource = resourcesDB.get(0);
				resource.setTitle(values[columnNames.indexOf("title")].trim());
				resource.setName(values[columnNames.indexOf("name")].trim());
				resource.setFullName(values[columnNames.indexOf("fullname")].trim());
				resource.setIdCard(values[columnNames.indexOf("id")].trim());
				resource.setMobile(values[columnNames.indexOf("mobile")].trim());
				resource.setEmail(values[columnNames.indexOf("email")].trim());
				resource.setAddress(values[columnNames.indexOf("address")].trim());
				if (!values[columnNames.indexOf("salary")].trim().equals("")) {
					resource.setSalary(numberFormat.parse(values[columnNames.indexOf("salary")].trim()).doubleValue());
				}
				resourcesDao.update(resource);
				resourceId = resource.getId();
			}

			if(projectId > 0){
				// Check project resource by id in the database.
				List<ProjectResources> projectResourcesDB = projectResourcesDao.getByProjectIdAndResourceId(projectId,
						resourceId, clientId);
				ProjectResources projectResource = null;
				if (null == projectResourcesDB || projectResourcesDB.size() < 1) {
					// Insert into ProjectResource.
					projectResource = new ProjectResources();
					projectResource.setProjectId(projectId);
					projectResource.setResourceId(resourceId);
					if (!values[columnNames.indexOf("quantity")].trim().equals("")) {
						projectResource.setQuantity(Integer.parseInt(values[columnNames.indexOf("quantity")].trim()));
					}
					projectResource.setClientId(clientId);

					projectResources.add(projectResource);
				} else {
					// Update ProjectResource.
					projectResource = projectResourcesDB.get(0);
					if (!values[columnNames.indexOf("quantity")].trim().equals("")) {
						projectResource.setQuantity(Integer.parseInt(values[columnNames.indexOf("quantity")].trim()));
					}

					projectResourcesDao.update(projectResource);
				}
			}
			
		}
		if (projectResources.size() > 0) {
			projectResourcesDao.insert(projectResources);
		}

		// Return result.
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", projectResources.size());
		return new Result(result, true);
	}

	@Override
	public Result filterTitle(int clientId) {
		final List<Resources> data = resourcesDao.filterTitle(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("workers", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getToExist(int clientId, int resourceId) {
		final List<Resources> data = resourcesDao.getToExist(clientId, resourceId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("resourcesexists", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getForBPMN(String code) {
		final List<Resources> data = resourcesDao.getForBPMN(code);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("resourcesexists", data);
		// Return.
		return new Result(result, true);
	}

}
