package com.oliveira.rest.restfulapi.exception;

import java.time.LocalDate;

public class ErrorDetails {
	
	private LocalDate timestamp;
	private String message;
	private String details;
	
	public ErrorDetails() {
		
	}
	
	public ErrorDetails(LocalDate timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
	

}
