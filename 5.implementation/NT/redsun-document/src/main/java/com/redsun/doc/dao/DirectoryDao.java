package com.redsun.doc.dao;


import java.util.List;

import com.redsun.doc.entities.Directory;
import com.redsun.doc.entities.DirectoryResource;
import com.redsun.doc.entities.Document;

public interface DirectoryDao{
	
    // Insert.
	Integer insert(final Directory directory) ;
	Integer insertDef(final Directory directory) ;
	void insertBatch(final List<Directory> directories);

    // Update.
	int update(final Directory directory) ;
	int updateDef(final Directory directory) ;
	void updateBatch(final List<Directory> directories);
	// Update location.
	int updateLocation(final String oldLocation, final String newLocation);
	int updateLocationDef(final String oldLocation, final String newLocation);

	// Delete.
	int delete(final Integer id) ;
	int deleteDef(final Integer id) ;
	int deleteDR(final Integer directoryId);
	int moveFolderToTrash(final int id, final int isTrash);

	// Get By Id.
	List<Directory> getById(final Integer id) ;
	List<Directory> getDirectoryDefById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

    // Exist name.
	boolean existName(final String location, final String name) ;
	boolean existNameDef(final String location, final String name) ;

    // Exist id and name.
	boolean existIdAndName(final Integer id, final String name) ;
	boolean existIdAndNameDef(final Integer id, final String name) ;
	
	List<Directory> listDirectory(int projectId, int clientId, String userName, int trash);
	List<Directory> listDirectory(int clientId, String userName, int trash);
	List<Directory> listDirectory(int id);
	List<Directory> listDefaultDirectory();
	List<Directory> getDirectoryByLocation(int id, String location);
	List<Directory> getDirectoryDefByLocation(int id, String location);
	List<Directory> getDirectoryByProjectInit(int projectId, int clientId, String userName, int trash);
	List<DirectoryResource> getDirectoryResource(int directoryId);
	List<Directory> getDirectoryFromTrash(int clientId);
	List<Document> listFiles(int directoryId);
	List<Document> listFiles(int directoryId, String fileName);
	List<Document> getFile(int id);
}