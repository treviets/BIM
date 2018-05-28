package com.redsun.service.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.service.entities.Resources;
import com.redsun.service.entities.Result;
import com.redsun.service.service.ResourcesService;
import com.redsun.service.utils.FileUtil;
import com.redsun.service.utils.RedSunConstants;
import com.redsun.service.validation.ResourcesValidator;

/**
 * Resources Controller
 */
@RestController
@RequestMapping("resourcesservice")
public class ResourcesController {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private Environment env;

	// Service.
	@Autowired
	ResourcesService resourcesService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder) {
		binder.setValidator(new ResourcesValidator());
	}

	// Insert.
	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Resources resources) {
		return resourcesService.insert(resources);
	}

	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody Resources resources) {
		return resourcesService.update(resources);
	}

	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id) {
		return resourcesService.delete(id);
	}

	// Get by Id.
	@RequestMapping(value = "getbyid/{clientId}/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("clientId") final Integer clientId, @PathVariable("id") final Integer id) {
		return resourcesService.getById(clientId, id);
	}

	// List for page and filter.
	@RequestMapping(value = "list/page/{clientId}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object listResourcesForPageAndFilter(@PathVariable("clientId") int clientId,
			@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo) {
		return resourcesService.listResourcesForPageAndFilter(clientId, itemsPerPage, pageNo);
	}

	// Get all.
	@RequestMapping(value = "list/{clientId}", method = { RequestMethod.GET })
	public Object getAll(@PathVariable("clientId") Integer clientId) {
		return resourcesService.listAll(clientId);
	}
	
	// Get all.
	@RequestMapping(value = "list-all-type/{clientId}", method = { RequestMethod.GET })
	public Object getAllWithAllType(@PathVariable("clientId") Integer clientId) {
		return resourcesService.listAllForAllType(clientId);
	}
		
	// Get all.
	@RequestMapping(value = "deleted/list/{clientId}", method = { RequestMethod.GET })
	public Object getAllDeletedResource(@PathVariable("clientId") Integer clientId) {
		return resourcesService.listAll(clientId, RedSunConstants.DELETED_FLAG);
	}
	
	// restore resource
	@RequestMapping(value = "restore/{resourceId}/{clientId}", method = { RequestMethod.GET })
	public Object restoreResource(@PathVariable("resourceId") Integer resourceId, @PathVariable("clientId") Integer clientId) {
		return resourcesService.restore(resourceId, clientId);
	}

	// filter by members were exists in project_resources
	@RequestMapping(value = "filtermember/{projectId}/{clientId}", method = { RequestMethod.GET })
	public Object filterMember(@PathVariable("projectId") final Integer projectId,
			@PathVariable("clientId") final Integer clientId) {
		return resourcesService.filterMember(projectId, clientId);
	}

	// count
	@RequestMapping(value = "count/{clientId}", method = { RequestMethod.GET })
	public Object countResources(@PathVariable("clientId") final Integer clientId) {
		Result count = resourcesService.count(clientId);
		return count;
	}

	// List for exterior
	@RequestMapping(value = "exterior/page/{clientId}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object listResourcesExterior(@PathVariable("clientId") int clientId,
			@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo) {
		return resourcesService.listResourcesExterior(clientId, itemsPerPage, pageNo);
	}

	// Import resource
	@RequestMapping(value = "import-resource", method = RequestMethod.POST)
	public Object importResource(@RequestParam("filePath") final String filePath,
			@RequestParam("projectId") final Integer projectId, @RequestParam("clientId") final Integer clientId,
			@RequestParam("file") final MultipartFile file) throws IOException, ParseException {
		// Save file to local server.
		final String absolutePath = servletContext.getRealPath("/") + env.getProperty(filePath)
				+ (projectId == null ? "" : (projectId.toString() + File.separatorChar));
		FileUtil.saveFileToLocal(absolutePath, file);
		// Call service.
		return resourcesService.importResources(projectId, clientId, file);
	}

	// filter title = worker
	@RequestMapping(value = "filtertitle/{clientId}", method = { RequestMethod.GET })
	public Object filterWorker(@PathVariable("clientId") final Integer clientId) {
		return resourcesService.filterTitle(clientId);
	}

	// get all to check exist
	@RequestMapping(value = "checkexist/{clientId}/{id}", method = { RequestMethod.GET })
	public Object getToExist(@PathVariable("clientId") final Integer clientId, @PathVariable("id") final Integer id) {
		return resourcesService.getToExist(clientId, id);
	}

	// get by project id and code
	@RequestMapping(value = "bpmn/{code}", method = { RequestMethod.GET })
	public Object getForBPMN(@PathVariable("code") final String code) {
		return resourcesService.getForBPMN(code);
	}
}
