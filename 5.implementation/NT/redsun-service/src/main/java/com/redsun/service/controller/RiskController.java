package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.Risk;
import com.redsun.service.service.RiskService;

@RestController
@RequestMapping(value = "risks")
public class RiskController {

	@Autowired
	RiskService riskService;

	@RequestMapping(value = "/{clientId}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object risk(@PathVariable("clientId") Integer clientId, @PathVariable("itemsPerPage") Integer itemsPerPage,
			@PathVariable("pageNo") Integer pageNo, Model model) {
		return riskService.getRisks(clientId, itemsPerPage, pageNo);
	}

	@RequestMapping(value = "/listforoneproject/{clientId}/{projectId}", method = { RequestMethod.GET })
	public Object getListRiskOneProject(@PathVariable("clientId") Integer clientId,
			@PathVariable("projectId") Integer projectId) {
		return riskService.getListRiskOneProject(clientId, projectId);
	}

	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public Object addRisk(@RequestBody Risk risk) {
		return riskService.insert(risk);

	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.PUT })
	public Object updateRisk(@PathVariable("id") final Integer id, @RequestBody Risk risk) {
		return riskService.update(risk);

	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
	public Object delete(@PathVariable("id") final Integer id) {
		return riskService.delete(id);
	}

	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") final Integer id) {
		return riskService.getById(id);
	}

}
