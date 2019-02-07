package org.openmrs.module.todolist.api.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.todolist.domains.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("todolist.TodoDao")
public class TodoDao {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private DbSessionFactory sessionFactory;
	
	public DbSessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(DbSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Todo saveTodo(Todo todo) {
		sessionFactory.getCurrentSession().saveOrUpdate(todo);
		return todo;
	}
	
	public Todo UpdateTodo(Todo todo) {
		sessionFactory.getCurrentSession().update(todo);
		return todo;
	}
	
	public List<Todo> getAllTodos() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Todo.class);
		criteria.addOrder(Order.asc("id"));
		
		return criteria.list();
	}
	
	public Todo getTodoById(Integer id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Todo.class);
		criteria.add(Restrictions.eq("id", id));
		return (Todo) criteria.uniqueResult();
	}
	
	public Todo getTodoByUUID(String UUID) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Todo.class);
		criteria.add(Restrictions.eq("uuid", UUID));
		return (Todo) criteria.uniqueResult();
	}
	
	public void deleteTodo(Todo todo) {
		sessionFactory.getCurrentSession().delete(todo);
	}
}
