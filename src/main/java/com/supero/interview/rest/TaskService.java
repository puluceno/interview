package com.supero.interview.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.supero.interview.data.TaskRepository;
import com.supero.interview.model.Task;

/**
 * @author Thiago Puluceno
 */
@Path("/")
@RequestScoped
public class TaskService {

	@Inject
	private Logger log;

	@Inject
	private Validator validator;

	@Inject
	private TaskRepository repository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> listAllTasks() {
		return repository.findAll();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object lookupTaskById(@PathParam("id") int id) {
		Task task = repository.findById(id);
		if (task == null) {
			return Response.Status.NOT_FOUND;
		}
		return task;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTask(Task task) {

		Response.ResponseBuilder builder = null;

		try {
			validateTask(task);
			repository.register(task);
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	private void validateTask(Task task) throws ConstraintViolationException, ValidationException {
		Set<ConstraintViolation<Task>> violations = validator.validate(task);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}

	private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
		log.fine("Validation completed. violations found: " + violations.size());
		Map<String, String> responseObj = new HashMap<>();
		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}
}
