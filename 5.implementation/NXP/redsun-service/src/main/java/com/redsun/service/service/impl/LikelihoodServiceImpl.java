package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.LikelihoodDao;
import com.redsun.service.entities.Likelihood;
import com.redsun.service.entities.Result;
import com.redsun.service.service.LikelihoodService;

@Service
public class LikelihoodServiceImpl implements LikelihoodService {

	@Autowired
	LikelihoodDao likelihoodDao;

	@Override
	public Result getLikelihood(int itemsPerPage, int pageNo) {
		final List<Likelihood> data  = likelihoodDao.listLikelihood(itemsPerPage, pageNo);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("likelihoods", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(Likelihood likelihood) {
		final int data = likelihoodDao.insert(likelihood);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("likelihoods", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result update(Likelihood likelihood) {
		final int data = likelihoodDao.update(likelihood);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("likelihoods", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(Integer id) {
		final int data = likelihoodDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("likelihoods", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listAllLikelihood(int clientId) {
		final List<Likelihood> data  = likelihoodDao.listAllLikelihood(clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("likelihoods", data);
		// Return.
		return new Result(result, true);
	}

}
