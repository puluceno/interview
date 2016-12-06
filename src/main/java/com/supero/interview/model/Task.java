package com.supero.interview.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Thiago Puluceno
 */
@Entity
@Table(schema = "supero")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t ORDER BY t.title"),
		@NamedQuery(name = "Task.findByTitle", query = "SELECT t FROM Task t WHERE t.title LIKE :title"),
		@NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description LIKE :description"),
		@NamedQuery(name = "Task.findByStatus", query = "SELECT t FROM Task t WHERE t.status = :status") })
public class Task implements Serializable {

	public static final String FIND_ALL = "Task.findAll";
	public static final String FIND_BY_TITLE = "Task.findByTitle";
	public static final String FIND_BY_DESCRIPTION = "Task.findByDescription";
	public static final String FIND_BY_STATUS = "Task.findByStatus";

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTask;
	@NotNull
	private String title;
	private String description;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastEditDate;

	public Task() {
	}

	public Task(Integer idTask) {
		this.idTask = idTask;
	}

	public Task(Integer idTask, String title, StatusEnum status, Date createdDate, Date lastEditDate) {
		this.idTask = idTask;
		this.title = title;
		this.status = status;
		this.createdDate = createdDate;
		this.lastEditDate = lastEditDate;
	}

	public Integer getIdTask() {
		return idTask;
	}

	public void setIdTask(Integer idTask) {
		this.idTask = idTask;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastEditDate() {
		return lastEditDate;
	}

	public void setLastEditDate(Date lastEditDate) {
		this.lastEditDate = lastEditDate;
	}

	@PrePersist
	public void createdAt() {
		this.createdDate = this.lastEditDate = new Date();
	}

	@PreUpdate
	public void updatedAt() {
		this.lastEditDate = new Date();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((idTask == null) ? 0 : idTask.hashCode());
		result = prime * result + ((lastEditDate == null) ? 0 : lastEditDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Task))
			return false;
		Task other = (Task) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idTask == null) {
			if (other.idTask != null)
				return false;
		} else if (!idTask.equals(other.idTask))
			return false;
		if (lastEditDate == null) {
			if (other.lastEditDate != null)
				return false;
		} else if (!lastEditDate.equals(other.lastEditDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [idTask=" + idTask + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", createdDate=" + createdDate + ", lastEditDate=" + lastEditDate + "]";
	}

}
