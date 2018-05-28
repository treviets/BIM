package com.redsun.service.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.entities.TaskEquipments;
import com.redsun.service.service.TaskEquipmentsTrackingService;

@RestController
@RequestMapping(value = "taskequipmenttracking")
public class TaskEquipmentsTrackingController {

	@Autowired
	ServletContext application;
	@Autowired
	TaskEquipmentsTrackingService taskEquipmentsTrackingService;

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object addTask(Model model, @RequestBody TaskEquipments taskEquipments) {
		return taskEquipmentsTrackingService.insert(taskEquipments);
	}
}
