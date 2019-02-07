package org.openmrs.module.todolist.web.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.todolist.api.TaskService;
import org.openmrs.module.todolist.domains.Task;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Resource(name = RestConstants.VERSION_1 + "/todolist/task", supportedClass = Task.class, supportedOpenmrsVersions = { "2.0.*, 2.1.*,2.2.*" })
public class TaskResource extends DataDelegatingCrudResource<Task> {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	private TaskService taskService = Context.getService(TaskService.class);
	
	private PatientService patientService = Context.getService(PatientService.class);
	
	@Override
	public Task getByUniqueId(String uuid) {
		return taskService.getTaskByuuid(uuid);
	}
	
	@Override
	protected void delete(Task task, String s, RequestContext requestContext) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException("Delete not supported on Task resource");
	}
	
	@Override
	public Task newDelegate() {
		return new Task();
	}
	
	@Override
	public Task save(Task task) {
		return null;
	}
	
	@Override
	public void purge(Task task, RequestContext requestContext) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException();
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		
		if (representation instanceof DefaultRepresentation | representation instanceof RefRepresentation) {
			description.addProperty("id");
			description.addProperty("uuid");
			description.addProperty("patient", Representation.DEFAULT);
			description.addProperty("name");
			description.addProperty("description");
			description.addProperty("completed");
			return description;
		} else if (representation instanceof FullRepresentation) {
			description.addProperty("id");
			description.addProperty("uuid");
			description.addProperty("patient", Representation.FULL);
			description.addProperty("name");
			description.addProperty("description");
			description.addProperty("completed");
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
		description.addRequiredProperty("patient");
		description.addProperty("name");
		description.addProperty("description");
		description.addProperty("completed");
		
		return description;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	@ResponseBody
	public List<Task> getAllTasks() {
		log.info("REST TASK ENDPOINT : => " + taskService.getAllTasks());
		return taskService.getAllTasks();
	}
	
	@Override
	protected NeedsPaging<Task> doGetAll(RequestContext context) throws ResponseException {
		List<Task> tasks = taskService.getAllTasks();
		return new NeedsPaging<Task>(tasks, context);
	}
	
	@PropertySetter("voided")
	public static void setVoided(Task instance, Boolean voided) {
		instance.setVoided(voided);
		if (voided) {
			instance.setVoidedBy(Context.getAuthenticatedUser());
			instance.setDateVoided(new Date());
		}
	}
	
	@PropertySetter("creator")
	public static void setCreator(Task instance, Boolean voided) {
		instance.setCreator(Context.getAuthenticatedUser());
		instance.setDateCreated(new Date());
		
	}
	
}
