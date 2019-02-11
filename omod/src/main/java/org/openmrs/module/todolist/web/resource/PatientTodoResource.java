package org.openmrs.module.todolist.web.resource;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.todolist.api.service.PatientTodoService;
import org.openmrs.module.todolist.domains.PatientTodo;
import org.openmrs.module.todolist.domains.Todo;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertySetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import java.util.Arrays;
import java.util.List;

@Resource(name = RestConstants.VERSION_1 + "/todolist/patienttodos", supportedClass = PatientTodo.class, supportedOpenmrsVersions = { "2.0.*, 2.1.*,2.2.*" })
public class PatientTodoResource extends DataDelegatingCrudResource<PatientTodo> {
	
	private PatientTodoService patientTodoService = Context.getService(PatientTodoService.class);
	
	@Override
	public PatientTodo getByUniqueId(String s) {
		return patientTodoService.getPatientTodoById(Integer.parseInt(s));
	}
	
	@Override
	protected void delete(PatientTodo patientTodo, String s, RequestContext requestContext) throws ResponseException {
		
	}
	
	@Override
	public PatientTodo newDelegate() {
		return new PatientTodo();
	}
	
	@Override
	public PatientTodo save(PatientTodo patientTodo) {
		return patientTodoService.savePatientTodo(patientTodo);
	}
	
	@Override
	public Object create(SimpleObject propertiesToCreate, RequestContext context) throws ResponseException {
		return super.create(propertiesToCreate, context);
	}
	
	@Override
	public SimpleObject getAuditInfo(PatientTodo delegate) throws Exception {
		return super.getAuditInfo(delegate);
	}
	
	@Override
	public void purge(PatientTodo patientTodo, RequestContext requestContext) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException();
	}
	
	@Override
	public Object update(String uuid, SimpleObject propertiesToUpdate, RequestContext context) throws ResponseException {
		return super.update(uuid, propertiesToUpdate, context);
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		
		if (representation instanceof DefaultRepresentation | representation instanceof RefRepresentation) {
			description.addProperty("id");
			description.addProperty("uuid");
			description.addProperty("patient", Representation.DEFAULT);
			description.addProperty("todos", Representation.DEFAULT);
			return description;
		} else if (representation instanceof FullRepresentation) {
			description.addProperty("id");
			description.addProperty("uuid");
			description.addProperty("patient", Representation.FULL);
			description.addProperty("todos", Representation.FULL);
			return description;
		} else {
			return null;
		}
		
	}
	
	@Override
	public List<String> getPropertiesToExposeAsSubResources() {
		return Arrays.asList("attributes");
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("id");
		description.addProperty("patient");
		description.addProperty("todos");
		
		return description;
	}
	
	@Override
	public DelegatingResourceDescription getUpdatableProperties() throws ResourceDoesNotSupportOperationException {
		return this.getCreatableProperties();
	}
	
	@Override
	protected NeedsPaging<PatientTodo> doGetAll(RequestContext context) throws ResponseException {
		List<PatientTodo> tasks = patientTodoService.getAllPatientTodos();
		return new NeedsPaging<PatientTodo>(tasks, context);
	}
	
	@PropertySetter("patient")
	public void setPatient(PatientTodo instance, Patient patient) {
		instance.setPatient(patient);
	}
	
	@PropertySetter("todos")
	public void setTodos(PatientTodo instance, Todo todos) {
		instance.setTodos(todos);
	}
}
