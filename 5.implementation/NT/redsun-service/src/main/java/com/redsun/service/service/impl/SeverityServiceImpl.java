package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.SeverityDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Severity;
import com.redsun.service.service.SeverityService;
import com.redsun.service.utils.RedSunConstants;

@Service
public class SeverityServiceImpl implements SeverityService {

	@Autowired
	SeverityDao severityDao;
	Result result = new Result();
	Result resultSeverity = new Result();

	@Override
	public Object getSeverity(int itemsPerPage, int pageNo) {
		List<Severity> listSeverity = severityDao.listSeverity(itemsPerPage, pageNo);
		result.setStatus(RedSunConstants.STATUS_SUCCESS_FLAG);
		result.setDescription(RedSunConstants.STATUS_SUCCESS_STRING);
		Map<Object, Object> mapSeverity = new HashMap<Object, Object>();
		mapSeverity.put("priority", listSeverity);
		result.setResult(mapSeverity);
		return result;
	}

	@Override
	public Object insert(Severity severity) {
		int rowEffect = severityDao.insert(severity);
		result.setStatus(rowEffect);
		result.setDescription(
				rowEffect > 0 ? RedSunConstants.STATUS_SUCCESS_STRING : RedSunConstants.STATUS_FAILED_STRING);
		return result;
	}

	@Override
	public Object update(Severity severity) {
		int rowEffect = severityDao.update(severity);
		result.setStatus(rowEffect);
		result.setDescription(
				rowEffect > 0 ? RedSunConstants.STATUS_SUCCESS_STRING : RedSunConstants.STATUS_FAILED_STRING);
		return result;
	}

	@Override
	public Object delete(Integer id) {
		int rowEffect = severityDao.delete(id);
		result.setStatus(rowEffect);
		result.setDescription(
				rowEffect > 0 ? RedSunConstants.STATUS_SUCCESS_STRING : RedSunConstants.STATUS_FAILED_STRING);
		return result;
	}

	@Override
	public Object listAllSeverities(int clientId) {
		// TODO Auto-generated method stub
				result.setStatus(RedSunConstants.STATUS_SUCCESS_FLAG);
				result.setDescription(RedSunConstants.STATUS_SUCCESS_STRING);
						List<Severity> listSeverities = severityDao.listAllSeverities(clientId);
						Map<Object, Object> mapper = new HashMap<Object, Object>();
						mapper.put("severities", listSeverities);
						result.setResult(mapper);
						return result;
					}
}
