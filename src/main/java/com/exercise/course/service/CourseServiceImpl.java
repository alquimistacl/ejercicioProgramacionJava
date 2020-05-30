package com.exercise.course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.exercise.course.model.Course;
import com.exercise.course.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	private static final String COURSE_ID_NOT_FOUND = "Course id not found";
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Course> getPaginatedCourses() {
		Pageable pageable = PageRequest.of(1, 10);
		courseRepository.findAll(pageable);
		

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getCourses() {
		List<Course> courses = new ArrayList<>();
		courseRepository.findAll().forEach(courses::add);
		return courses;
	}

	@Override
	public Course getCourse(Long id) {
		Optional<Course> courseFinded = courseRepository.findById(id);
		if (!courseFinded.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, COURSE_ID_NOT_FOUND);
		}
		return courseFinded.get();
	}

	@Override
	public Boolean postCourse(Course course) {
		Boolean response = Boolean.FALSE;
		Course savedCourse = courseRepository.save(course);
		if (null != savedCourse.getId()) {
			response = Boolean.TRUE;
		}
		return response;
	}

	@Override
	public Boolean putCourse(Long id, Course course) {
		Boolean response = Boolean.FALSE;
		
		if (!courseRepository.findById(id).isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,COURSE_ID_NOT_FOUND);
		}
		
		int updateCourseById = courseRepository.updateCourseById(course.getName(), course.getCode(), id);

		if (updateCourseById > 0) {
			response = Boolean.TRUE;
		}
		return response;
	}

	@Override
	public Boolean deleteCourse(Long id) {
		Boolean response = Boolean.FALSE;
		if (courseRepository.findById(id).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, COURSE_ID_NOT_FOUND);
		}

		courseRepository.deleteById(id);

		if (courseRepository.findById(id).isEmpty()) {
			response = Boolean.TRUE;
		}

		return response;
	}

}
