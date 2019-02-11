package org.openmrs.module.todolist.api.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.todolist.api.dao.PatientTodoDao;
import org.openmrs.module.todolist.api.service.PatientTodoService;
import org.openmrs.module.todolist.api.utils.RandomUtil;
import org.openmrs.module.todolist.domains.PatientTodo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PatientTodoServieImpl extends BaseOpenmrsService implements PatientTodoService {
	
	@Autowired
	PatientTodoDao dao;
	
	Log log = LogFactory.getLog(getClass());
	
	public void setDao(PatientTodoDao dao) {
		this.dao = dao;
	}
	
	@Override
	public PatientTodo getPatientTodoById(Integer id) throws APIException {
		return dao.getPatientTodoById(id);
	}
	
	@Override
	public PatientTodo savePatientTodo(PatientTodo patientTodo) throws APIException {
		patientTodo.setUuid(RandomUtil.unique());
		if (patientTodo.getPatient() == null) {
			patientTodo.setPatient(Context.getPatientService().getPatient(7));
			patientTodo.setCreator(Context.getAuthenticatedUser().getCreator());
		}
		return dao.savePatientTodo(patientTodo);
	}
	
	@Override
	public PatientTodo updatePatientTodo(PatientTodo task) throws APIException {
		return null;
	}
	
	@Override
	public List<PatientTodo> getAllPatientTodos() throws APIException {
		return dao.getAllPatientTodos();
	}
	
	@Override
	public void voidPatientTodo(PatientTodo task, String voidReason) throws APIException {
		
	}
}
