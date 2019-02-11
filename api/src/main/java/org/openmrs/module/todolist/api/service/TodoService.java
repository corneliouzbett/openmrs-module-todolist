package org.openmrs.module.todolist.api.service;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.todolist.config.TodolistConfig;
import org.openmrs.module.todolist.domains.Todo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TodoService extends OpenmrsService {
	
	@Authorized()
	@Transactional(readOnly = true)
	Todo getTodoById(Integer id) throws APIException;
	
	@Authorized()
	@Transactional(readOnly = true)
	Todo getTodoByUUID(String uuid) throws APIException;
	
	@Authorized(TodolistConfig.MODULE_PRIVILEGE)
	@Transactional
	Todo saveTodo(Todo todo) throws APIException;
	
	@Authorized(TodolistConfig.MODULE_PRIVILEGE)
	@Transactional
	Todo updateTodo(Todo todo) throws APIException;
	
	@Transactional
	List<Todo> getAllTodo() throws APIException;
	
	@Transactional
	void voidTodo(Todo todo, String voidedReason) throws APIException;
	
}
