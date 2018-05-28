package com.redsun.service.controller;

import com.redsun.service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "customers")
public class CustomerController { 
	
	
	@Autowired
    CustomerService customerService;
	  
    @RequestMapping(value="/{clientId}/{code}/{name}/{itemsPerPage}/{pageNo}", method = {RequestMethod.GET})
    public Object customer(@PathVariable("clientId") int clientId
							, @PathVariable("code") String code
							, @PathVariable("name") String name
    						, @PathVariable("itemsPerPage") int itemsPerPage
    						, @PathVariable("pageNo") int pageNo, Model model) {
        return customerService.getCustomers(clientId, code, name, itemsPerPage, pageNo);
    }
    @RequestMapping(value="/list/{clientId}", method = {RequestMethod.GET})
    public Object getAll(@PathVariable("clientId") int clientId
							, Model model) {
        return customerService.getAllCustomers(clientId);
    }
}
