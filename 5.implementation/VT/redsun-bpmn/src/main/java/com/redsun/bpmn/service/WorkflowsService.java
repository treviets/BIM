package com.redsun.bpmn.service;

import com.redsun.bpmn.entities.Workflows;
import com.redsun.bpmn.entities.Result;

/**
 * Workflows Service interface
 */
public interface WorkflowsService {
	
    // Insert.
	Result insert(final Workflows workflows) ;

    // Update.
	Result update(final Workflows workflows) ;

    // Delete.
	Result delete(final Integer id) ;

	// Get By Id.
	Result getById(final Integer id) ;

    // Exists.
	Result exists(final Integer id) ;

    // Count.
	Result count() ;

	// List for page and filter.
	Result listWorkflowsForPageAndFilter(final int itemsPerPage, final int pageNo, final Workflows workflows);

}
