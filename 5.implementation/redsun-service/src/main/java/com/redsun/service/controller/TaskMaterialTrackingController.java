package com.redsun.service.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.TaskMaterialTracking;
import com.redsun.service.service.TaskMaterialTrackingService;

@RestController
@RequestMapping(value = "taskmaterialtracking")
public class TaskMaterialTrackingController {

	@Autowired
	ServletContext application;
	@Autowired
	TaskMaterialTrackingService taskMaterialTrackingService;

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object addTask(Model model, @RequestBody TaskMaterialTracking taskMaterialTracking) {
		return taskMaterialTrackingService.insert(taskMaterialTracking);
	}
}
