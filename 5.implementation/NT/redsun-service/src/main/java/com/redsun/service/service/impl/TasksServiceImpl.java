package com.redsun.service.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.redsun.service.dao.ResourcesDao;
import com.redsun.service.dao.TasksDao;
import com.redsun.service.entities.Resources;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Task;
import com.redsun.service.service.TasksService;
import com.redsun.service.utils.TaskEnums;

@Service
public class TasksServiceImpl implements TasksService {

	@Autowired
	TasksDao taskDao;
	@Autowired
	ResourcesDao resourceDao;

	@Override
	public Result getTasks(int clientId, int itemsPerPage, int pageNo) {
		final List<Task> data = taskDao.listTask(clientId, itemsPerPage, pageNo);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("tasks", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(Task task) {
		final int data = taskDao.insert(task);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("tasks", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result update(Task task) {
		final int data = taskDao.update(task);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("tasks", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(int id) {
		final int data = taskDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("tasks", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getById(int id) {
		final Task data = taskDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("tasks", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getTasksOneProject(int clientId, int projectId, String userName) {
		int resourceId = 0;
		List<Resources> resource = null;
		if (!userName.equals("admin")) {
			resource = resourceDao.getForBPMN(userName);
			if(resource != null && resource.size() > 0){
				resourceId = resource.get(0).getId();
			}
		}
		final List<Task> data = taskDao.listTaskOfOneProject(clientId, projectId, resourceId, userName);
		List<Task> tasksBacklog = new ArrayList<Task>();
		List<Task> tasksTodo = new ArrayList<Task>();
		List<Task> tasksInprogress = new ArrayList<Task>();
		List<Task> tasksDone = new ArrayList<Task>();
		List<Task> tasksBlock = new ArrayList<Task>();
		for (Task task : data) {
			if (task.getStatusId() == TaskEnums.BACKLOG.code()) {
				tasksBacklog.add(task);
			} else if (task.getStatusId() == TaskEnums.TODO.code()) {
				tasksTodo.add(task);
			} else if (task.getStatusId() == TaskEnums.INPROCESS.code()) {
				tasksInprogress.add(task);
			} else if (task.getStatusId() == TaskEnums.DONE.code()) {
				tasksDone.add(task);
			} else if (task.getStatusId() == TaskEnums.BLOCK.code()) {
				tasksBlock.add(task);
			}
		}
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("tasks", data);
		result.put("taskBacklog", tasksBacklog);
		result.put("taskTodo", tasksTodo);
		result.put("taskInprogress", tasksInprogress);
		result.put("taskDone", tasksDone);
		result.put("taskBlock", tasksBlock);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result importTasks(int projectId, int clientId, String userName, MultipartFile file)
			throws IOException, ParseException {
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
		// Import
		List<Task> taskList = new ArrayList<Task>();
		List<String> rows = IOUtils.readLines(file.getInputStream(), StandardCharsets.UTF_16);
		int rowNum = rows.size();
		System.out.println("rowNum: " + rowNum);
		if (rowNum < 2) {
			rows = IOUtils.readLines(file.getInputStream(), StandardCharsets.UTF_8);
			rowNum = rows.size();
		}
		// Get column names.
		List<String> columnNames = new ArrayList<String>();
		List<String> arrayWbs = new ArrayList<>();
		String row = rows.get(0);
		String[] values = row.split("\t");
		int colNum = values.length;
		// get column name
		for (int i = 0; i < colNum; i++) {
			columnNames.add(values[i].trim().toLowerCase());
		}
		for (int n = 1; n < rowNum; n++) {
			row = rows.get(n);
			values = row.split("\t");
			String s1 = values[columnNames.indexOf("wbs")].trim(); // get value
			
			arrayWbs.add(s1);
		}
		// Insert resource.
		for (int i = 1; i < rowNum; i++) {
			Task task = null;
			task = new Task();
			row = rows.get(i);
			values = row.split("\t");
			if (values.length == 0) {
				continue;
			} else if (values.length < colNum) {
				final Map<String, Object> result = new HashMap<String, Object>();
				result.put("count", i - 1);
				// Return.
				return new Result(result, false);
			}

			// start compare
			String string = values[columnNames.indexOf("wbs")].trim();
			String reverse = new StringBuffer(string).reverse().toString();
			char[] arrayChar = reverse.toCharArray(); // add reverse in char
			String child = "";
			String parent = "";
			int count = 0;
			for (int j = arrayChar.length - 1; j >= 0; j--) {
				if (arrayChar[j] == '.') {
					count++;
					if (count == 1) {
						child = string.substring(0, j);
						parent = new StringBuffer(child).reverse().toString();
					}

				}
			} // end compare

			// get parentId
			for (int a = 0; a < arrayWbs.size(); a++) {
					if (arrayWbs.get(a).equalsIgnoreCase(child)) {
						task.setParentId(projectId+"_"+arrayWbs.get(a));
				}
			}
			task.setWbs(projectId + "_"+values[columnNames.indexOf("wbs")].trim());
			task.setTaskName(values[columnNames.indexOf("name")].trim());
			task.setProjectId(projectId);
			task.setStatusId(15);
			task.setTypeId(27);
			task.setPriorityId(1);
			task.setTaskOwner(userName.toString());
			if (!values[columnNames.indexOf("duration")].trim().equals("")) {
				task.setEstimateTime(Integer.parseInt(values[columnNames.indexOf("duration")].trim()));
			}
			Date startDate = dt1.parse(values[columnNames.indexOf("start_date")].trim());
			task.setStartDate(startDate);
			Date endDate = dt1.parse(values[columnNames.indexOf("finish_date")].trim());
			task.setEndDate(endDate);
			task.setClientId(clientId);
			taskList.add(task);
		}
		if (taskList.size() > 0) {
			for (Task i : taskList) {
				taskDao.insert(i);
			}
		}

		// Return result.
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", taskList.size());
		return new Result(result, true);
	}

	@Override
	public Result insertListTasks(List<Task> tasks) {
		final Map<String, Object> result = new HashMap<String, Object>();
		for(Task task : tasks){
			final int data = taskDao.insert(task);
			result.put("tasks", data);
		}
		// Return.
		return new Result(result, true);
	}
	

	//----------------------------------------------
	// 4D: TODO: Replace named from wbs to code.
	//----------------------------------------------

	// Save list of tasks by project id and code.
	public Result saveTasksByProjectIdAndCode(List<Task> tasks) {
		Map<String, Object> result = new HashMap<String, Object>();
		int count = 0;
		for(Task task : tasks) {
			int exist = taskDao.countByProjectIdAndCode(task.getProjectId(), task.getWbs());
			if(exist < 1) {
				taskDao.insert(task);
				count++;
			} else {
				// TODO: update some fields.
			}
		}
		
		result.put("count", count);
		return new Result(result, true);
	}
	
	// Delete multiple by project id and codes.
	public Result deleteByProjectIdAndCodes(Integer projectId, List<String> codes) {
		Map<String, Object> result = new HashMap<String, Object>();
		int count = 0;
		for(String code : codes) {
			int count1 = taskDao.deleteByProjectIdAndCode(projectId, code);
			if(count1 > 0) {
				count++;
			}
		}
		
		result.put("count", count);
		return new Result(result, true);
	}
	
	// Count by project id and code.
	public Result countByProjectIdAndCode(Integer projectId, String code) {
		Map<String, Object> result = new HashMap<String, Object>();
		int count = taskDao.countByProjectIdAndCode(projectId, code);
		
		result.put("count", count);
		return new Result(result, true);
	}

	// List of tasks by project id and codes.
	public Result listTasksByProjectIdAndCodes(Integer projectId, List<String> codes) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Task> tasks = taskDao.listTasksByProjectIdAndCodes(projectId, codes);
		
		result.put("tasks", tasks);
		return new Result(result, true);
	}
	
}