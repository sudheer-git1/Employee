package com.employee.dto;

import java.time.LocalDateTime;

public class ErrorDetails {
	private LocalDateTime time;
	private String message;
	private String status;

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ErrorDetails(LocalDateTime time, String message, String status) {
		super();
		this.time = time;
		this.message = message;
		this.status = status;
	}

}
