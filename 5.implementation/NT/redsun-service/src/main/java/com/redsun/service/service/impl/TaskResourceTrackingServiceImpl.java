package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.TaskResourceTrackingDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.TaskResourceTracking;
import com.redsun.service.service.TaskResourceTrackingService;

@Service
public class TaskResourceTrackingServiceImpl implements TaskResourceTrackingService {

	@Autowired
	TaskResourceTrackingDao taskResourceTrackingDao;

	@Override
	public Result insert(TaskResourceTracking taskResourceTracking) {
		final int data = taskResourceTrackingDao.insert(taskResourceTracking);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("resouretracking", data);
		// Return.
		return new Result(result, true);
	}
}
