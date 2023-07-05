package com.oliveira.oliveirawebapp.entities.enums;

public enum TaskStatus {

	NOT_DONE(1),
	DONE(2),
	OPEN(3),
	CANCELED(4);

	private int code;

	private TaskStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static TaskStatus valueOf(int code) {
		for (TaskStatus value : TaskStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid TaskStatus code");
	}
}