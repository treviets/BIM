package com.redsun.service.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.service.entities.Materials;
import com.redsun.service.entities.Result;
import com.redsun.service.service.MaterialsService;
import com.redsun.service.utils.FileUtil;

@RestController
@RequestMapping(value = "materialservice")
public class MaterialsController {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private Environment env;

	@Autowired
	MaterialsService materialsService;

	// get all
	@RequestMapping(value = "/listall/{clientId}", method = { RequestMethod.GET })
	public Object getAll(@PathVariable("clientId") final Integer clientId) {
		return materialsService.getAll(clientId);
	}

	// filter materials were exist in project_materials
	@RequestMapping(value = "/filtermaterial/{projectId}/{clientId}", method = { RequestMethod.GET })
	public Object filterMaterial(@PathVariable("projectId") final Integer projectId,
			@PathVariable("clientId") final Integer clientId) {
		return materialsService.filterMaterial(projectId, clientId);
	}

	// List for page
	@RequestMapping(value = "list/page/{clientId}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object listMaterialsForPage(@PathVariable("clientId") int clientId,
			@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo) {
		return materialsService.listMaterialsForPage(clientId, itemsPerPage, pageNo);
	}

	// Insert.
	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Materials material) {
		return materialsService.insert(material);
	}

	// count
	@RequestMapping(value = "count/{clientId}", method = { RequestMethod.GET })
	public Object countMaterials(@PathVariable("clientId") final Integer clientId) {
		Result count = materialsService.count(clientId);
		return count;
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody Materials material) {
		return materialsService.update(material);
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id) {
		return materialsService.delete(id);
	}

	// Get by Id.
	@RequestMapping(value = "getbyid/{id}/{clientId}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") final Integer id, @PathVariable("clientId") final Integer clientId) {
		return materialsService.getById(id, clientId);
	}

	// check exist
	@RequestMapping(value = "checkexist/{clientId}/{id}", method = { RequestMethod.GET })
	public Object checkExist(@PathVariable("id") final Integer id, @PathVariable("clientId") final Integer clientId) {
		return materialsService.checkExist(clientId, id);
	}

	// Import material
	@RequestMapping(value = "import-material", method = RequestMethod.POST)
	public Object importMaterial(@RequestParam("filePath") final String filePath,
			@RequestParam("projectId") final Integer projectId, @RequestParam("clientId") final Integer clientId,
			@RequestParam("file") final MultipartFile file) throws IOException, ParseException {
		// Save file to local server.
		final String absolutePath = servletContext.getRealPath("/") + env.getProperty(filePath)
				+ (projectId == null ? "" : (projectId.toString() + File.separatorChar));
		FileUtil.saveFileToLocal(absolutePath, file);
		// Call service.
		return materialsService.importMaterials(projectId, clientId, file);
	}
}
