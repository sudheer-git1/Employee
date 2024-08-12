package com.employee.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employee.dto.ErrorDetails;

@RestControllerAdvice
public class EmployeeExceptionHandler {
	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<ErrorDetails> geterror(DateTimeParseException e) {
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Invalid date format");
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorDetails> geterror(ValidationException e) {
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Invalid data");
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}
}
