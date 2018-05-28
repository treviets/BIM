package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Likelihood;

public interface LikelihoodDao {
	// select
	 List<Likelihood> listLikelihood(int itemsPerPage, int pageNo);
	 
	// select all
		 List<Likelihood> listAllLikelihood(int clientId);

	// insert
	 int insert(Likelihood likelihood);

	// update
	int update(Likelihood likelihood);

	// delete
	 int delete(Integer id);
	 

}