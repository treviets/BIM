package com.redsun.service.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ProjectDao;
import com.redsun.service.dao.ResourcesDao;
import com.redsun.service.dao.TasksDao;
import com.redsun.service.entities.Link;
import com.redsun.service.entities.Project;
import com.redsun.service.entities.Resources;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Task;
import com.redsun.service.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectDao projectDao;
	@Autowired
	TasksDao taskDao;
	@Autowired
	ResourcesDao resourceDao;
	
	@Override
	public Result listProjects(int clientId, int itemsPerPage, int pageNo, Integer... projectType) {
		final List<Project> data = projectDao.listProjects(clientId, itemsPerPage, pageNo, projectType);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projects", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getById(int projectId) {
		final Project project = projectDao.getById(projectId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projects", project);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result insert(Project project) {
		final int data = projectDao.insert(project);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projects", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listAllProject(int clientId, Integer... projectType) {
		final List<Project> data = projectDao.listAllProjects(clientId, projectType);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projects", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result delete(int projectId) {
		final int data = projectDao.delete(projectId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projects", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result update(Project project) {
		final int data = projectDao.update(project);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projects", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getByProjectId(int projectId) {
		final List<Project> data = projectDao.getByProjectId(projectId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projects", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getGanttChart(int clientId, int projectId, String userName) {
		int resourceId = 0;
		List<Resources> resource = null;
		if(!userName.equals("admin")){
			resource = resourceDao.getForBPMN(userName);
			if(resource != null && resource.size() > 0){
				resourceId = resource.get(0).getId();
			}
			
		}
		final List<Task> tasks = taskDao.listTaskOfOneProject(clientId, projectId, resourceId, userName);
		List<Link> links = new ArrayList<>();
		int index = 1;
		for(Task t: tasks){
			if(t.getParentId() != null && !"0".equals(t.getParent())){
				Link link = new Link();
				link.setId(index);
				int parentId = getTaskParentId(tasks, t.getParentId());
				link.setSource(String.valueOf(parentId));
				t.setParent(parentId);
				link.setTarget(String.valueOf(t.getId()));
				link.setType("1");
				links.add(link);
				index += 1;
			}
		}
		final Map<String, Object> data = new HashMap<String, Object>();
		data.put("data", tasks);
		data.put("links", links);
		
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskData", data);
		return new Result(result, true);
	}
	
	@Override
	public Result getProjectByResourceId(String resourceCode) {
		final List<Project> data = projectDao.getProjectByResourceId(resourceCode);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("projects", data);
		// Return.
		return new Result(result, true);
	}
	
	//just use for gantt chart
	private int getTaskParentId(List<Task> tasks, String parentId){
		for(Task t: tasks){
			if(t.getWbs() != null && t.getWbs().equals(parentId)){
				return t.getId();
			}
		}
		return 0;
	}
}
