package com.oliveira.oliveirawebapp.entities;

import java.time.LocalDate;

import com.oliveira.oliveirawebapp.entities.enums.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity(name="tb_task")
public class Task {
	
	
	@Id
	@GeneratedValue
	private int id;
	private String username;
	
	@Size(min=10, message="You must enter at least 10 characters")
	private String description;
	
	@Column(name="target_date")
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
