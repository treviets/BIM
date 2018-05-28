package com.redsun.service.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.redsun.service.entities.Result;
import com.redsun.service.entities.Task;

public interface TasksService {

	// select tasks
	Result getTasks(int clientId, int itemsPerPage, int pageNo);

	// select tasks of one the project
	Result getTasksOneProject(int clientId, int projectId, String userName);

	// insert
	Result insert(Task task);
	
	// insert
	Result insertListTasks(List<Task> task);

	// update
	Result update(Task task);

	// delete
	Result delete(int id);

	// delete
	Result getById(int id);

	// // Import task by file.
	Result importTasks(int projectId, int clientId, String userName, MultipartFile file)
			throws IOException, ParseException;
	
	
	//----------------------------------------------
	// 4D
	//----------------------------------------------
	
	/**
	 * Save list of tasks by project id and code.
	 * @param tasks
	 * @return
	 */
	Result saveTasksByProjectIdAndCode(List<Task> tasks);
	
	/**
	 * Delete multiple by codes and project id.
	 * @param projectId
	 * @param codes
	 * @return
	 */
	Result deleteByProjectIdAndCodes(Integer projectId, List<String> codes);
	
	/**
	 * Count by project id and code.
	 * @param projectId
	 * @param code
	 * @return
	 */
	Result countByProjectIdAndCode(Integer projectId, String code);
	
	/**
	 * List of tasks by project id and codes.
	 * @param projectId
	 * @param codes
	 * @return
	 */
	Result listTasksByProjectIdAndCodes(Integer projectId, List<String> codes);

}
