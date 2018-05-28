package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.TaskMaterialTrackingDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.TaskMaterialTracking;
import com.redsun.service.service.TaskMaterialTrackingService;

@Service
public class TaskMaterialTrackingServiceImpl implements TaskMaterialTrackingService {

	@Autowired
	TaskMaterialTrackingDao taskMaterialTrackingDao;

	@Override
	public Result insert(TaskMaterialTracking taskMaterialTracking) {
		final int data = taskMaterialTrackingDao.insert(taskMaterialTracking);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("materialtracking", data);
		// Return.
		return new Result(result, true);
	}
}
