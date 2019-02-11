package org.openmrs.module.todolist.api.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.todolist.domains.PatientTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("todolist.patientTodoDao")
public class PatientTodoDao {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private DbSessionFactory sessionFactory;
	
	public DbSessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(DbSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public PatientTodo savePatientTodo(PatientTodo patientTodo) {
		sessionFactory.getCurrentSession().saveOrUpdate(patientTodo);
		return patientTodo;
	}
	
	public PatientTodo UpdatePatientTodo(PatientTodo patientTodo) {
		sessionFactory.getCurrentSession().update(patientTodo);
		return patientTodo;
	}
	
	public List<PatientTodo> getAllPatientTodos() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PatientTodo.class);
		criteria.addOrder(Order.asc("id"));
		
		return criteria.list();
	}
	
	public PatientTodo getPatientTodoById(Integer id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PatientTodo.class);
		criteria.add(Restrictions.eq("id", id));
		return (PatientTodo) criteria.uniqueResult();
	}
}
