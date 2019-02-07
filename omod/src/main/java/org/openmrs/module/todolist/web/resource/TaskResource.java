package org.openmrs.module.todolist.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.todolist.api.service.TaskService;
import org.openmrs.module.todolist.domains.Task;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
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

@Resource(name = RestConstants.VERSION_1 + "/todolist/task", supportedClass = Task.class, supportedOpenmrsVersions = { "2.0.*, 2.1.*,2.2.*" })
public class TaskResource extends DataDelegatingCrudResource<Task> {
	
	private TaskService taskService = Context.getService(TaskService.class);
	
	@Override
	public Task getByUniqueId(String id) {
		return taskService.getTaskById(Integer.parseInt(id));
	}
	
	@Override
	protected void delete(Task task, String s, RequestContext requestContext) throws ResponseException {
		taskService.voidTask(task, s);
	}
	
	@Override
	public Task newDelegate() {
		return new Task();
	}
	
	@Override
	public Task save(Task task) {
		return taskService.saveTask(task);
	}
	
	@Override
	public Object retrieve(String uuid, RequestContext context) throws ResponseException {
		return super.retrieve(uuid, context);
	}

	@Override
	public Object create(SimpleObject propertiesToCreate, RequestContext context) throws ResponseException {
		return super.create(propertiesToCreate, context);
	}
	
	@Override
	public SimpleObject getAuditInfo(Task delegate) throws Exception {
		return super.getAuditInfo(delegate);
	}
	
	@Override
	public void purge(Task task, RequestContext requestContext) throws ResponseException {
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
		description.addProperty("patient");
		description.addProperty("name");
		description.addProperty("description");
		description.addProperty("completed");
		
		return description;
	}
	
	@Override
	public DelegatingResourceDescription getUpdatableProperties() throws ResourceDoesNotSupportOperationException {
		return this.getCreatableProperties();
	}
	
	/*	@RequestMapping(method = RequestMethod.GET, value = "/all")
		@ResponseBody
		public List<Task> getAllTodos() {
			log.info("REST TASK ENDPOINT : => " + taskService.getAllTodos());
			return taskService.getAllTodos();
		}*/
	
	@Override
	protected NeedsPaging<Task> doGetAll(RequestContext context) throws ResponseException {
		List<Task> tasks = taskService.getAllTasks();
		return new NeedsPaging<Task>(tasks, context);
	}
	
	/*	@PropertySetter("voided")
		public static void setVoided(Task instance, Boolean voided) {
			instance.setVoided(voided);
			if (voided) {
				instance.setVoidedBy(Context.getAuthenticatedUser());
				instance.setDateVoided(new Date());
			}
		}*/
	
	/*	@PropertySetter("creator")
		public static void setCreator(Task instance, Boolean voided) {
			instance.setCreator(Context.getAuthenticatedUser().getCreator());
			instance.setDateCreated(new Date());
			
		}*/
	
}
