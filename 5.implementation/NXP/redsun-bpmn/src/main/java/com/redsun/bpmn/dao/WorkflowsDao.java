package com.redsun.bpmn.dao;

import java.util.List;

import com.redsun.bpmn.entities.Workflows;

/**
 * Workflows DAO interface
 */
public interface WorkflowsDao {
	
    // Insert.
	Integer insert(final Workflows workflows) ;

    // Update.
	int update(final Workflows workflows) ;

    // Delete.
	int delete(final Integer id) ;

	// Get By Id.
	List<Workflows> getById(final Integer id) ;

    // Exists.
	boolean exists(final Integer id) ;

    // Count.
	long count() ;

	// List for page and filter.
	List<Workflows> listWorkflowsForPageAndFilter(final int itemsPerPage, final int pageNo, final Workflows workflows);

}
