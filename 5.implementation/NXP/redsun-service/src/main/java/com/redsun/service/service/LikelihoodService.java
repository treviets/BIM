package com.redsun.service.service;

import com.redsun.service.entities.Likelihood;
import com.redsun.service.entities.Result;

public interface LikelihoodService {
	// get on page
	Result getLikelihood(int itemsPerPage, int pageNo);

	// get list all
	Result listAllLikelihood(int clientId);

	// insert
	Result insert(Likelihood likelihood);

	// update
	Result update(Likelihood likelihood);

	// delete
	Result delete(Integer id);

}
