package com.redsun.doc.service;

import java.io.IOException;
import java.util.List;

import com.redsun.doc.entities.Directory;
import com.redsun.doc.entities.DirectoryResource;
import com.redsun.doc.entities.Result;

public interface DirectoryResourceService{
	
    // Insert.
	Result insert(final List<DirectoryResource> directoryResources) throws IOException;
	Result getResourceAssigned(int clientId);
	Result getResourceAssignedById(int clientId, int directoryId);
}