package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.TaskEquipmentsTrackingDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.TaskEquipments;
import com.redsun.service.service.TaskEquipmentsTrackingService;

@Service
public class TaskEquipmentTrackingServiceImpl implements TaskEquipmentsTrackingService{

	@Autowired
	TaskEquipmentsTrackingDao taskEquipmentsTrackingDao;

	@Override
	public Result insert(TaskEquipments taskEquipments) {
		final int data = taskEquipmentsTrackingDao.insert(taskEquipments);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("equipmenttracking", data);
		// Return.
		return new Result(result, true);
	}
}
