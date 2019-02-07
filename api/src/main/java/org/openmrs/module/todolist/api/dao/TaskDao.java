/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.todolist.api.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.todolist.domains.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("todolist.TaskDao")
public class TaskDao {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private DbSessionFactory sessionFactory;
	
	public DbSessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(DbSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Task saveItem(Task task) {
		sessionFactory.getCurrentSession().saveOrUpdate(task);
		return task;
	}
	
	public List<Task> getAllTasks() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
		criteria.addOrder(Order.asc("name"));
		
		return criteria.list();
	}
	
	public Task getTaskByUuid(String uuid) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
		criteria.add(Restrictions.eq("uuid", uuid.toLowerCase()));
		return (Task) criteria.uniqueResult();
	}
}
