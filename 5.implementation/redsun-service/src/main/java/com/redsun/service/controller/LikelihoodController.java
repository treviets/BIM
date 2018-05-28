package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.redsun.service.entities.Likelihood;
import com.redsun.service.service.LikelihoodService;

@RestController
@EnableWebMvc
@RequestMapping(value = "likelihoods")
public class LikelihoodController {

	@Autowired
	LikelihoodService likelihoodService;

	@RequestMapping(value = "/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object getLikelihood(@PathVariable("itemsPerPage") int itemsPerPage, @PathVariable("pageNo") int pageNo,
			Model model) {
		return likelihoodService.getLikelihood(itemsPerPage, pageNo);
	}


	@RequestMapping(value = "/list/{clientId}", method = { RequestMethod.GET })
	public Object listAllLikelihood(@PathVariable("clientId") final Integer clientId) {
		return likelihoodService.listAllLikelihood(clientId);
	}
	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public Object addLikelihood(@RequestBody Likelihood likelihood) {
		return likelihoodService.insert(likelihood);

	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.POST })
	public Object updateLikelihood(@PathVariable("id") final Integer id, @RequestBody Likelihood likelihood) {
		return likelihoodService.update(likelihood);

	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
	public Object deleteLikelihood(@PathVariable("id") final Integer id) {
		return likelihoodService.delete(id);
	}
}
