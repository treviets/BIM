package com.redsun.doc.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.redsun.doc.dao.DirectoryDao;
import com.redsun.doc.dao.DirectoryResourceDao;
import com.redsun.doc.dao.DocumentDao;
import com.redsun.doc.entities.Directory;
import com.redsun.doc.entities.DirectoryResource;
import com.redsun.doc.entities.Document;
import com.redsun.doc.entities.Result;
import com.redsun.doc.service.DirectoryResourceService;
import com.redsun.doc.service.DirectoryService;
import com.redsun.doc.utils.FileUtil;
import com.redsun.doc.utils.ReferencablesConstants;



@Service
public class DirectoryResourceServiceImpl implements DirectoryResourceService{
	
	@Autowired
	private DirectoryResourceDao directoryResourceDao;
	
	
    // Insert.
	@Override
	public Result insert(final List<DirectoryResource> directoryResources) throws IOException {
		List<Integer> ids = new ArrayList<Integer>();
		for(int i=0; i<directoryResources.size(); i++)
			directoryResourceDao.deleteFolderAssignedToTrash(directoryResources.get(i).getDirectoryId(), directoryResources.get(i).getUserName());
		for(int i=0; i<directoryResources.size(); i++)
			directoryResourceDao.deleteDirectoryResource(directoryResources.get(i).getResourceId());
		for(int i=0; i<directoryResources.size(); i++){
			if(directoryResources.get(i).getDirectoryId() != 0){
				final int id = directoryResourceDao.insert(directoryResources.get(i));
				ids.add(id);
			}
		}
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("ids", ids);
		return new Result(result, true);
	}


	@Override
	public Result getResourceAssigned(int clientId) {
		// TODO Auto-generated method stub
		List<Directory> listResourceAssigned = directoryResourceDao.getResourceAssigned(clientId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("listResourceAssigned", listResourceAssigned);
		return new Result(result, true);
	}
	
	@Override
	public Result getResourceAssignedById(int clientId, int directoryId) {
		// TODO Auto-generated method stub
		List<Directory> listResourceAssignedById = directoryResourceDao.getResourceAssignedById(clientId, directoryId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("listResourceAssignedById", listResourceAssignedById);
		return new Result(result, true);
	}

}