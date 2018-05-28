package com.redsun.service.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.TaskResourceTracking;
import com.redsun.service.service.TaskResourceTrackingService;

@RestController
@RequestMapping(value = "taskresourcetracking")
public class TaskResourceTrackingController {

	@Autowired
	ServletContext application;
	@Autowired
	TaskResourceTrackingService taskResourceTrackingService;

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object addTask(Model model, @RequestBody TaskResourceTracking taskResourceTracking) {
		return taskResourceTrackingService.insert(taskResourceTracking);
	}
}
