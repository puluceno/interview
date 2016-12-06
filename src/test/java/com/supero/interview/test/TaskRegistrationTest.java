package com.supero.interview.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.supero.interview.data.TaskRepository;
import com.supero.interview.model.StatusEnum;
import com.supero.interview.model.Task;

/**
 * @author Thiago Puluceno
 */
public class TaskRegistrationTest {

	@Inject
	TaskRepository repository;

	@Inject
	Logger log;

	// @Test
	public void testRegister() throws Exception {
		Task newTask = new Task();
		newTask.setTitle("New Title");
		newTask.setDescription("new Description");
		newTask.setStatus(StatusEnum.OPEN);
		repository.register(newTask);
		assertNotNull(newTask.getIdTask());
		log.info(newTask.getTitle() + " was persisted with id " + newTask.getIdTask());
	}

}
