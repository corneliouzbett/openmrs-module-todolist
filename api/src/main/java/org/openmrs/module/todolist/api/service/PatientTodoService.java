package org.openmrs.module.todolist.api.service;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.todolist.config.TodolistConfig;
import org.openmrs.module.todolist.domains.PatientTodo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PatientTodoService extends OpenmrsService {
	
	@Authorized()
	@Transactional(readOnly = true)
	PatientTodo getPatientTodoById(Integer id) throws APIException;
	
	@Authorized(TodolistConfig.MODULE_PRIVILEGE)
	@Transactional
	PatientTodo savePatientTodo(PatientTodo task) throws APIException;
	
	@Authorized(TodolistConfig.MODULE_PRIVILEGE)
	@Transactional
	PatientTodo updatePatientTodo(PatientTodo task) throws APIException;
	
	@Transactional
	List<PatientTodo> getAllPatientTodos() throws APIException;
	
	@Transactional
	void voidPatientTodo(PatientTodo task, String voidReason) throws APIException;
}
