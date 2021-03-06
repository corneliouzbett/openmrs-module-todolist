/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * <p>
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.todolist.api.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.todolist.api.dao.TaskDao;
import org.openmrs.module.todolist.api.service.TaskService;
import org.openmrs.module.todolist.api.utils.RandomUtil;
import org.openmrs.module.todolist.domains.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class TaskServiceImpl extends BaseOpenmrsService implements TaskService {
	
	@Autowired
	TaskDao dao;
	
	Log log = LogFactory.getLog(getClass());
	
	public void setDao(TaskDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Task getTaskById(Integer id) throws APIException {
		log.error(dao.getTaskById(id));
		return dao.getTaskById(id);
	}
	
	@Override
	public Task saveTask(Task task) throws APIException {
		task.setUuid(RandomUtil.unique());
		if (task.getPatient() == null) {
			task.setPatient(Context.getPatientService().getPatient(7));
			task.setCreator(Context.getAuthenticatedUser().getCreator());
		}
		return dao.saveTask(task);
		
	}
	
	@Override
	public Task updateTask(Task task) throws APIException {
		task.setChangedBy(Context.getAuthenticatedUser().getChangedBy());
		task.setDateChanged(new Date());
		return dao.saveTask(task);
	}
	
	@Override
	public List<Task> getAllTasks() throws APIException {
		log.error(dao.getAllTasks());
		return dao.getAllTasks();
	}
	
	@Override
	public void voidTask(Task task, String voidReason) throws APIException {
		task.setVoided(true);
		task.setVoidedBy(Context.getAuthenticatedUser());
		task.setVoidReason(voidReason);
		task.setDateVoided(new Date());
		dao.saveTask(task);
	}
	
}
