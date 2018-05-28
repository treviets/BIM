package com.redsun.doc.dao;


import java.util.List;

import com.redsun.doc.entities.Document;

public interface DocumentDao{
	
    // Insert.
	Integer insert(final Document document) ;

    // Update.
	int update(final Document document) ;
	
	// Update location.
	int updateLocation(final String oldLocation, final String newLocation);
	
	// Update location by ref.
	int updateDocumentByRef(final String ref_type, final Integer ref_id, final String location);

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Document> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;
	
}