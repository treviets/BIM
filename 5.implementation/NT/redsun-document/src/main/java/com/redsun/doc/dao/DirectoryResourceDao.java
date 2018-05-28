package com.redsun.doc.dao;

import java.util.List;

import com.redsun.doc.entities.Directory;
import com.redsun.doc.entities.DirectoryResource;

public interface DirectoryResourceDao{
	
    // Insert.
	Integer insert(final DirectoryResource directoryResource) ;
	int deleteDirectoryResource(final Integer resourceId) ;
	int deleteFolderAssignedToTrash(final int directoryId, final String userName);
	List<Directory> getResourceAssigned(int clientId);
	List<Directory> getResourceAssignedById(int clientId, int directoryId);
}