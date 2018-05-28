package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.redsun.service.service.UnitsService;

@RestController
@EnableWebMvc
@RequestMapping(value = "units")
public class UnitsController {

	@Autowired
	UnitsService unitsService;

	@RequestMapping(value = "/list/{clientId}", method = { RequestMethod.GET })
	public Object listAllLikelihood(@PathVariable("clientId") final Integer clientId) {
		return unitsService.listAllUnits(clientId);
	}
}
