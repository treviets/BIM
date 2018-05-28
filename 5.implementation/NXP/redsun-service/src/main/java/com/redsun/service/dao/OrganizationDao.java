package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Organization;

public interface OrganizationDao {
	// select
	List<Organization> listAll(int clientId);

}
