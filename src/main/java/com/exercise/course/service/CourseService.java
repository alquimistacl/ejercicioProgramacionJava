package com.exercise.course.service;

import java.util.List;

import com.exercise.course.model.Course;

public interface CourseService {

	public List<Course> getPaginatedCourses();

	public List<Course> getCourses();

	public Course getCourse(Long id);

	public Boolean postCourse(Course course);

	public Boolean putCourse(Long id, Course course);

	public Boolean deleteCourse(Long id);
}
