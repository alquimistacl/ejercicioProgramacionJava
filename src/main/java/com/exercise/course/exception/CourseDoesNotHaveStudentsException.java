package com.exercise.course.exception;

/**
 * Exception to handle when the course does not have students
 * @author Luis San Martin
 *
 */
public class CourseDoesNotHaveStudentsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8271437043894505107L;

	public CourseDoesNotHaveStudentsException(String message) {
		super(message);
	}

}
