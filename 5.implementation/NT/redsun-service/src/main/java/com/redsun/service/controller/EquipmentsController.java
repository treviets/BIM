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

import com.redsun.service.entities.Equipments;
import com.redsun.service.entities.Result;
import com.redsun.service.service.EquipmentsService;
import com.redsun.service.utils.FileUtil;

@RestController
@RequestMapping(value = "equipmentservice")
public class EquipmentsController {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private Environment env;

	@Autowired
	EquipmentsService equipmentsService;

	// get all
	@RequestMapping(value = "/listall/{clientId}", method = { RequestMethod.GET })
	public Object getAll(@PathVariable("clientId") final Integer clientId) {
		return equipmentsService.getAll(clientId);
	}

	// filter Equipments were exist in project_equipments
	@RequestMapping(value = "filterequipment/{projectId}/{clientId}", method = { RequestMethod.GET })
	public Object filterEquipment(@PathVariable("projectId") final Integer projectId,
			@PathVariable("clientId") final Integer clientId) {
		return equipmentsService.filterEquipment(projectId, clientId);
	}

	// List for page
	@RequestMapping(value = "list/page/{clientId}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object listEquipmentsForPage(@PathVariable("clientId") int clientId,
			@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo) {
		return equipmentsService.listEquipmentsForPage(clientId, itemsPerPage, pageNo);
	}

	// Insert.
	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Equipments equipment) {
		return equipmentsService.insert(equipment);
	}

	// count
	@RequestMapping(value = "count/{clientId}", method = { RequestMethod.GET })
	public Object countEquipments(@PathVariable("clientId") final Integer clientId) {
		Result count = equipmentsService.count(clientId);
		return count;
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody Equipments equipment) {
		return equipmentsService.update(equipment);
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id) {
		return equipmentsService.delete(id);
	}

	// Get by Id.
	@RequestMapping(value = "getbyid/{id}/{clientId}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") final Integer id, @PathVariable("clientId") final Integer clientId) {
		return equipmentsService.getById(id, clientId);
	}

	// check exist
	@RequestMapping(value = "checkexist/{clientId}/{id}", method = { RequestMethod.GET })
	public Object checkExist(@PathVariable("id") final Integer id, @PathVariable("clientId") final Integer clientId) {
		return equipmentsService.checkExist(clientId, id);
	}

	// Import equipment
	@RequestMapping(value = "import-equipment", method = RequestMethod.POST)
	public Object importEquipment(@RequestParam("filePath") final String filePath,
			@RequestParam("projectId") final Integer projectId, @RequestParam("clientId") final Integer clientId,
			@RequestParam("file") final MultipartFile file) throws IOException, ParseException {
		// Save file to local server.
		final String absolutePath = servletContext.getRealPath("/") + env.getProperty(filePath)
				+ (projectId == null ? "" : (projectId.toString() + File.separatorChar));
		FileUtil.saveFileToLocal(absolutePath, file);
		// Call service.
		return equipmentsService.importEquipments(projectId, clientId, file);
	}
}
