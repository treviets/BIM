package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Resource;

public interface ResourceDao {
	 List<Resource> listResources(int clientId, String code, String name, int itemsPerPage, int pageNo);
	 List<Resource> listResources(int clientId);
	 List<Resource> getResourceDetails(int id);
}
