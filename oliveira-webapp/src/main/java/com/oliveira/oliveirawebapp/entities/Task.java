package com.oliveira.oliveirawebapp.entities;

import java.time.LocalDate;

import com.oliveira.oliveirawebapp.entities.enums.TaskStatus;

public class Task {
	
	
	private int id;
	private String username;
	private String description;
	private LocalDate targetDate;
	TaskStatus status;
	
	public Task() {
		
	};
	
	public Task(int id, String username, String description, LocalDate targetDate, TaskStatus status) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", status=" + status + "]";
	}

	
	
}
