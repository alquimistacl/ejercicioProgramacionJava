package com.exercise.course.exception;

public class CourseDoesNotHaveStudentsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8271437043894505107L;

	public CourseDoesNotHaveStudentsException(String message) {
		super(message);
	}

}
