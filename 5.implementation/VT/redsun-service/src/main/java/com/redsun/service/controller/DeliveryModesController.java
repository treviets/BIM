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

import com.redsun.service.entities.DeliveryModes;
import com.redsun.service.service.DeliveryModesService;
import com.redsun.service.validation.DeliveryModesValidator;

/**
 * DeliveryModes Controller
 */
@RestController
@RequestMapping("deliverymodesservice")
public class DeliveryModesController {
	
	// Service.
	@Autowired
	DeliveryModesService deliveryModesService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new DeliveryModesValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody DeliveryModes deliveryModes){
		return deliveryModesService.insert(deliveryModes);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody DeliveryModes deliveryModes){
		return deliveryModesService.update(deliveryModes);
	}
	
	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id){
		return deliveryModesService.delete(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return deliveryModesService.getById(id);
	}
	
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listDeliveryModesForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody DeliveryModes deliveryModes) {
		return deliveryModesService.listDeliveryModesForPageAndFilter(itemsPerPage, pageNo, deliveryModes);
	}

}
