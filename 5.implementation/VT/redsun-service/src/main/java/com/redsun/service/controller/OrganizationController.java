package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.service.OrganizationService;

@RestController
@RequestMapping(value = "organizations")
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;

	@RequestMapping(value = "/list/{clientId}", method = { RequestMethod.GET })
	public Object listAll(@PathVariable("clientId") final Integer clientId,
			Model model) {
		return organizationService.listAll(clientId);
	}
}
