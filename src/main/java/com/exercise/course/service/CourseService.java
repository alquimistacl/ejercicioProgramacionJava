package com.exercise.course.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.exercise.course.model.Course;
import com.exercise.course.model.CourseEntity;

public interface CourseService {

	public Page<CourseEntity> getPaginatedCourses(Integer page, Integer size);

	public List<CourseEntity> getCourses();

	public CourseEntity getCourse(Long id);

	public Long saveCourse(Course course);

	public Long updateCourse(Long id, Course course);

	public Long deleteCourse(Long id);
}
