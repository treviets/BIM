package com.redsun.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.redsun.service.ClientService;
import com.redsun.utils.UserUtil;

@Controller
@EnableWebMvc
@RequestMapping(value = "/project-timeline")
public class ProjectTimelineController {

	@Autowired
	ClientService clientService;

	@Autowired
	UserUtil userUtil;
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String index(Model model) {
		return "project-timeline";
	}
}
