package org.openmrs.module.todolist.api.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.todolist.api.dao.TodoDao;
import org.openmrs.module.todolist.api.service.TodoService;
import org.openmrs.module.todolist.api.utils.RandomUtil;
import org.openmrs.module.todolist.config.TodolistConfig;
import org.openmrs.module.todolist.domains.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class TodoServiceImpl extends BaseOpenmrsService implements TodoService {
	
	@Autowired
	TodoDao dao;
	
	Log log = LogFactory.getLog(getClass());
	
	public void setDao(TodoDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Todo getTodoById(Integer id) throws APIException {
		return dao.getTodoById(id);
	}
	
	@Override
	public Todo getTodoByUUID(String uuid) throws APIException {
		return dao.getTodoByUUID(uuid);
	}
	
	@Override
	public Todo saveTodo(Todo todo) throws APIException {

        todo.setUuid(RandomUtil.unique());
		
		return dao.saveTodo(todo);
	}
	
	@Override
	public Todo updateTodo(Todo todo) throws APIException {
		return dao.UpdateTodo(todo);
	}
	
	@Override
	public List<Todo> getAllTodo() throws APIException {
		return dao.getAllTodos();
	}
	
	@Override
	@Authorized(TodolistConfig.MODULE_PRIVILEGE)
	@Transactional
	public void voidTodo(Todo todo, String voidReason) throws APIException {
		todo.setVoided(true);
		todo.setVoidedBy(Context.getAuthenticatedUser());
		todo.setVoidReason(voidReason);
		todo.setDateVoided(new Date());
		dao.saveTodo(todo);
	}
	
}
