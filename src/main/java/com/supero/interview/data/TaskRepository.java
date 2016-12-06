package com.supero.interview.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.supero.interview.model.StatusEnum;
import com.supero.interview.model.Task;

/**
 * @author Thiago Puluceno
 */
@SuppressWarnings("unchecked")
@Stateless
public class TaskRepository {

	@Inject
	private EntityManager em;

	/**
	 * Finds a single task using its ID as parameter.
	 * 
	 * @param id
	 * @return Task
	 */
	public Task findById(Integer id) {
		return em.find(Task.class, id);
	}

	/**
	 * Finds all tasks, by default they are ordered by title.
	 * 
	 * @return List containing all the tasks found
	 */
	public List<Task> findAll() {
		return em.createNamedQuery(Task.FIND_ALL).getResultList();
	}

	/**
	 * Finds all tasks containing the given string in the title.
	 * 
	 * @param title
	 * @return List containing all the tasks found
	 */
	public List<Task> findByTitle(String title) {
		return em.createNamedQuery(Task.FIND_BY_TITLE).setParameter("title", title).getResultList();
	}

	/**
	 * Finds all tasks containing the given string in the description.
	 * 
	 * @param description
	 * @return List containing all the tasks found
	 */
	public List<Task> findByDescription(String description) {
		return em.createNamedQuery(Task.FIND_BY_DESCRIPTION).setParameter("description", description).getResultList();
	}

	/**
	 * Finds all tasks with the given status.
	 * 
	 * @param status
	 * @return List containing all the tasks found
	 */
	public List<Task> findByStatus(StatusEnum status) {
		return em.createNamedQuery(Task.FIND_BY_STATUS).setParameter("status", status).getResultList();
	}

	/**
	 * Persists the new task into the database.
	 * 
	 * @param task
	 */
	public void register(Task task) {
		em.persist(task);
		em.flush();
	}

}
