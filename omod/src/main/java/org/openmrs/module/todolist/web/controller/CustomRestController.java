package org.openmrs.module.todolist.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.todolist.api.service.TaskService;
import org.openmrs.module.todolist.domains.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// @Controller
public class CustomRestController {
	
	private TaskService taskService = Context.getService(TaskService.class);
	
	//@RequestMapping(value = "patienttodos/tasks/all")
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}
}
