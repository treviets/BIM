package com.redsun.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.service.service.CommentsService;

@RestController
@RequestMapping(value = "commentservice")
public class CommentsController {

	@Autowired
	CommentsService commentsService;

	@RequestMapping(value = "/getbytaskid/{taskId}", method = { RequestMethod.GET })
	public Object getListByTaskId(@PathVariable("taskId") Integer taskId){
		return commentsService.listComments(taskId);
	}
}
