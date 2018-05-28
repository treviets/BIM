package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.Milestones;
import com.redsun.service.service.MilestonesService;
import com.redsun.service.validation.MilestonesValidator;

/**
 * Milestones Controller
 */
@RestController
@RequestMapping("milestonesservice")
public class MilestonesController {
	
	// Service.
	@Autowired
	MilestonesService milestonesService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new MilestonesValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody Milestones milestones){
		return milestonesService.insert(milestones);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody Milestones milestones){
		return milestonesService.update(milestones);
	}
	
	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id){
		return milestonesService.delete(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return milestonesService.getById(id);
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listMilestonesForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody Milestones milestones) {
		return milestonesService.listMilestonesForPageAndFilter(itemsPerPage, pageNo, milestones);
	}

	// List by projectId.
	@RequestMapping(value = "list/{projectId}", method = { RequestMethod.GET })
	public Object listMilestonesByProjectId(@PathVariable("projectId") int projectId) {
		return milestonesService.listMilestonesByProjectId(projectId);
	}

}
