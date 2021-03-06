package com.exercise.course.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class allows to handle the exceptions to be showed in a clear way
 * @author Luis San Martin
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ StudentNotFoundException.class, CourseNotFoundException.class })
	public ResponseEntity<GenericError> handleNotFoundException(Exception ex) {

		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		GenericError notFoundError = buildErrorTrace(ex);

		return new ResponseEntity<>(notFoundError, httpStatus);
	}

	@ExceptionHandler({ CourseDoesNotHaveStudentsException.class })
	public ResponseEntity<GenericError> handleInternalError(Exception ex) {

		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		GenericError notFoundError = buildErrorTrace(ex);

		return new ResponseEntity<>(notFoundError, httpStatus);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

	private GenericError buildErrorTrace(Exception ex) {
		GenericError error = new GenericError();
		error.setUserMessage(ex.getMessage());
		return error;
	}

}