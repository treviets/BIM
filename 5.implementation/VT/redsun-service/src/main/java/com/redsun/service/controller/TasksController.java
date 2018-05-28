package com.redsun.service.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.service.entities.Task;
import com.redsun.service.service.TasksService;
import com.redsun.service.utils.FileUtil;

@RestController
@RequestMapping(value = "tasks")
public class TasksController {

	@Autowired
	ServletContext application;

	@Autowired
	private Environment env;
	@Autowired
	TasksService tasksService;

	@RequestMapping(value = "/list/{clientId}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
	public Object listTasks(@PathVariable("clientId") Integer clientId,
			@PathVariable("itemsPerPage") Integer itemsPerPage, @PathVariable("pageNo") Integer pageNo) {
		return tasksService.getTasks(clientId, itemsPerPage, pageNo);
	}

	@RequestMapping(value = "/listforoneproject/{clientId}/{projectId}/{userName}", method = { RequestMethod.GET })
	public Object listTasksOneProject(@PathVariable("projectId") Integer projectId,
			@PathVariable("clientId") Integer clientId, @PathVariable("userName") String userName) {
		return tasksService.getTasksOneProject(clientId, projectId, userName);
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Object addTask(Model model, @RequestBody Task task) {
		return tasksService.insert(task);
	}
	
	@RequestMapping(value = "/4d/add-list-tasks", method = { RequestMethod.POST })
	public Object addListTask(Model model, @RequestBody List<Task> tasks) {
		return tasksService.insertListTasks(tasks);
	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.PUT })
	public Object updateActivities(@PathVariable("id") final Integer id, Model model, @RequestBody Task task) {
		return tasksService.update(task);
	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
	public Object deleteActivities(Model model, @PathVariable("id") final Integer id) {

		return tasksService.delete(id);
	}

	// Get by Id.
	@RequestMapping(value = "getbyid/{id}", method = { RequestMethod.GET })
	public Object getById(@PathVariable("id") final Integer id) {
		return tasksService.getById(id);
	}

	// Import task
	@RequestMapping(value = "import-task", method = RequestMethod.POST)
	public Object importTask(@RequestParam("filePath") final String filePath,
			@RequestParam("projectId") final Integer projectId, @RequestParam("clientId") final Integer clientId,
			@RequestParam("userName") final String userName, @RequestParam("file") final MultipartFile file)
			throws IOException, ParseException {
		// Save file to local server.
		final String absolutePath = application.getRealPath("/") + env.getProperty(filePath)
				+ (projectId == null ? "" : (projectId.toString() + File.separatorChar));
		FileUtil.saveFileToLocal(absolutePath, file);
		// Call service.
		return tasksService.importTasks(projectId, clientId, userName, file);
	}	

	
	//----------------------------------------------
	// 4D: TODO: Replace named from wbs to code.
	//----------------------------------------------

	// Save list of tasks by project id and code.
	@RequestMapping(value = "/saveTasksByProjectIdAndCode", method = RequestMethod.POST)
	public Object saveTasksByProjectIdAndCode(@RequestBody List<Task> tasks) {
		return tasksService.saveTasksByProjectIdAndCode(tasks);
	}
	
	// Delete multiple by project id and codes.
	@RequestMapping(value = "/deleteByProjectIdAndCodes", method = RequestMethod.DELETE)
	public Object deleteByProjectIdAndCodes(@RequestParam("projectId") Integer projectId, @RequestBody List<String> codes) {
		return tasksService.deleteByProjectIdAndCodes(projectId, codes);
	}
	
	// Count by project id and code.
	@RequestMapping(value = "/countByProjectIdAndCode", method = RequestMethod.GET)
	public Object countByProjectIdAndCode(@RequestParam("projectId") Integer projectId, @RequestParam("code") String code) {
		return tasksService.countByProjectIdAndCode(projectId, code);
	}

	// List of tasks by project id and codes.
	@RequestMapping(value = "/listTasksByProjectIdAndCodes", method = RequestMethod.POST)
	public Object listTasksByProjectIdAndCodes(@RequestParam("projectId") Integer projectId, @RequestBody List<String> codes) {
		return tasksService.listTasksByProjectIdAndCodes(projectId, codes);
	}

}
