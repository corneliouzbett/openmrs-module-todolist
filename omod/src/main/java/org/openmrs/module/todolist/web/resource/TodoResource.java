package org.openmrs.module.todolist.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.todolist.api.service.TodoService;
import org.openmrs.module.todolist.domains.Todo;
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

import java.util.List;

@Resource(name = RestConstants.VERSION_1 + "/todolist/todo", supportedClass = Todo.class, supportedOpenmrsVersions = { "2.0.*, 2.1.*,2.2.*" })
public class TodoResource extends DataDelegatingCrudResource<Todo> {
	
	private TodoService todoService = Context.getService(TodoService.class);
	
	@Override
	public Todo getByUniqueId(String s) {
		return todoService.getTodoById(Integer.parseInt(s));
	}
	
	@Override
	protected void delete(Todo todo, String s, RequestContext requestContext) throws ResponseException {
		todoService.voidTodo(todo, s);
	}
	
	@Override
	public Todo newDelegate() {
		return new Todo();
	}
	
	@Override
	public Todo save(Todo todo) {
		return todoService.saveTodo(todo);
	}
	
	@Override
	public void purge(Todo todo, RequestContext requestContext) throws ResponseException {
		throw new UnsupportedOperationException();
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
			description.addProperty("title");
			description.addProperty("description");
			return description;
		} else if (representation instanceof FullRepresentation) {
			description.addProperty("id");
			description.addProperty("uuid");
			description.addProperty("title");
			description.addProperty("description");
			return description;
		} else {
			return null;
		}
	}
	
	@Override
	protected NeedsPaging<Todo> doGetAll(RequestContext context) throws ResponseException {
		List<Todo> tasks = todoService.getAllTodo();
		return new NeedsPaging<Todo>(tasks, context);
	}

	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("id");
		description.addProperty("uuid");
		description.addProperty("title");
		description.addProperty("description");

		return description;
	}

	@Override
	public DelegatingResourceDescription getUpdatableProperties() throws ResourceDoesNotSupportOperationException {
		return this.getCreatableProperties();
	}
}
