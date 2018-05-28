package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Task;

public interface TasksDao {

	// select
	List<Task> listTask(int clientId, int itemsPerPage, int pageNo);

	// insert
	int insert(Task task);

	// update

	int update(Task task);

	// delete
	int delete(int id);

	// get by id
	Task getById(int id);

	// select all tasks of one the project
	List<Task> listTaskOfOneProject(int clientId, int projectId, int resouceId, String userName);
	
	
	//----------------------------------------------
	// 4D
	//----------------------------------------------
	
	/**
	 * Delete by project id and code.
	 * @param projectId
	 * @param code
	 * @return
	 */
	Integer deleteByProjectIdAndCode(Integer projectId, String code);
	
	/**
	 * Count by project id and code.
	 * @param projectId
	 * @param code
	 * @return
	 */
	Integer countByProjectIdAndCode(Integer projectId, String code);
	
	/**
	 * List of tasks by project id and codes.
	 * @param projectId
	 * @param codes
	 * @return
	 */
	List<Task> listTasksByProjectIdAndCodes(Integer projectId, List<String> codes);

}
