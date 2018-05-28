package com.redsun.doc.service;

import java.io.IOException;
import java.util.List;

import com.redsun.doc.entities.Directory;
import com.redsun.doc.entities.Result;

public interface DirectoryService{
	
    // Insert.
	Result insert(final Directory directory) throws IOException;
	Result insertDef(final Directory directory) throws IOException;
	Result insertBatch(int projectId, String projectName, String userName, int clientId) throws IOException;
    // Update.
	Result update(final Directory directory) throws IOException;
	Result updateDef(final Directory directory) throws IOException;

    // Delete.
	Result delete(final Integer id, final String lo) throws IOException;
	Result deleteDef(final Integer id, final String lo) throws IOException;
	
	 // Delete.
	Result moveFolderToTrash(final int id, final int isTrash, final String location) throws IOException;
	
	Result undoFolderFromTrash(final String id, final int isTrash) throws IOException;

	// Get By Id.
	Result getById(final Integer id);
	Result getDirectoryDefById(final Integer id);

    // Exists.
	Result exists(final Integer id);

    // Count.
	Result count() ;
	
	Result listDirectory(int projectId, int clientId, String userName);
	Result listDirectory(int clientId, String userName, int trash);
	Result listDefaultDirectory();
	Result listDirectoryByUser(int clientId, String userName, int trash);
	Result listDocument(int directoryId);
	Result getDirectoryFromTrash(int clientId);
	Result download(final Integer id);
}