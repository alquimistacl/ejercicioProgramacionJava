package com.exercise.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class SpringExceptionHandler extends ExceptionHandlerExceptionResolver {

	@ExceptionHandler({ ExpiredJwtException.class })
	public ResponseEntity<GenericError> handleExpiredJWTException(ExpiredJwtException ex) {
		HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
		GenericError notFoundError = buildErrorTrace(ex);
		return new ResponseEntity<>(notFoundError, httpStatus);
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<GenericError> handleIllegalArgumentException(IllegalArgumentException ex) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		GenericError notFoundError = buildErrorTrace(ex);
		return new ResponseEntity<>(notFoundError, httpStatus);
	}

	@ExceptionHandler({ MissingBearerException.class })
	public ResponseEntity<GenericError> handleUnAuthorizedException(MissingBearerException ex) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		GenericError error = buildErrorTrace(ex);
		return new ResponseEntity<>(error, httpStatus);
	}

	private GenericError buildErrorTrace(Exception ex) {
		GenericError error = new GenericError();
		error.setUserMessage(ex.getMessage());
		return error;
	}
}