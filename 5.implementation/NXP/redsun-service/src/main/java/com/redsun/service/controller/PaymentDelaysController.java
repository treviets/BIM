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

import com.redsun.service.entities.PaymentDelays;
import com.redsun.service.service.PaymentDelaysService;
import com.redsun.service.validation.PaymentDelaysValidator;

/**
 * PaymentDelays Controller
 */
@RestController
@RequestMapping("paymentdelaysservice")
public class PaymentDelaysController {
	
	// Service.
	@Autowired
	PaymentDelaysService paymentDelaysService;

	// InitBinder.
	@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new PaymentDelaysValidator());
	}

	// Insert.
	@RequestMapping(value = "insert", method = { RequestMethod.POST })
	public Object insert(@Validated @RequestBody PaymentDelays paymentDelays){
		return paymentDelaysService.insert(paymentDelays);
	}
	
	// Update.
	@RequestMapping(value = "update/{id}", method = { RequestMethod.PUT })
	public Object update(@PathVariable("id") Integer id, @RequestBody PaymentDelays paymentDelays){
		return paymentDelaysService.update(paymentDelays);
	}
	
	// Delete.
	@RequestMapping(value = "delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") Integer id){
		return paymentDelaysService.delete(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") Integer id) {
		return paymentDelaysService.getById(id);
	}
	
	// Get by Id.
	@RequestMapping(value = "getall/{clientId}", method = { RequestMethod.GET })
	public Object getAll(@PathVariable("clientId") Integer clientId) {
		return paymentDelaysService.listAll(clientId);
	}
	// List for page and filter.
	@RequestMapping(value = "list/page/filter/{itemsPerPage}/{pageNo}", method = { RequestMethod.POST })
	public Object listPaymentDelaysForPageAndFilter(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo, @RequestBody PaymentDelays paymentDelays) {
		return paymentDelaysService.listPaymentDelaysForPageAndFilter(itemsPerPage, pageNo, paymentDelays);
	}

}
