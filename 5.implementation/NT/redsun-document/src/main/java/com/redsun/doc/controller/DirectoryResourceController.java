package com.redsun.doc.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.doc.dao.impl.DirectoryDaoImpl;
import com.redsun.doc.entities.DirectoryResource;
import com.redsun.doc.entities.Result;
import com.redsun.doc.service.DirectoryResourceService;


@RestController
@RequestMapping(value = "directoryresource")
public class DirectoryResourceController { 
	
	
	@Autowired
    DirectoryResourceService directoryResourceService;
	private static Logger log = Logger.getLogger(DirectoryResourceController.class);
	// Insert.
	@RequestMapping(value = "/insert", method = { RequestMethod.POST })
	public List<Object> insert(@RequestBody List<DirectoryResource> directoryResources
						, @RequestParam("clientId") int clientId) throws IOException {
		
		for(int i=0; i<directoryResources.size(); i++){
			directoryResources.get(i).setClientId(clientId);
		}
		Result result = directoryResourceService.insert(directoryResources);
		List<Object> results = new ArrayList<Object>();
		results.add(result);
		return results;
	}
	
	@RequestMapping(value="/list/resourceassignedbyid/{clientId}/{directoryId}", method = {RequestMethod.GET})
    public Object getResourceAssigned(@PathVariable("clientId") int clientId, @PathVariable("directoryId") int directoryId, Model model) {
    	
        return directoryResourceService.getResourceAssignedById(clientId, directoryId);
    }
}
