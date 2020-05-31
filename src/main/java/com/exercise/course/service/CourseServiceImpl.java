package com.exercise.course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.exercise.course.dao.CourseRepository;
import com.exercise.course.model.Course;
import com.exercise.course.model.CourseEntity;

@Service
public class CourseServiceImpl implements CourseService {

	private static final String COURSE_ID_NOT_FOUND = "Course id not found";
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Page<CourseEntity> getPaginatedCourses(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return courseRepository.findAll(pageable);
	}

	@Override
	public List<CourseEntity> getCourses() {
		List<CourseEntity> courses = new ArrayList<>();
		courseRepository.findAll().forEach(courses::add);
		return courses;
	}

	@Override
	public CourseEntity getCourse(Long id) {
		Optional<CourseEntity> courseFinded = courseRepository.findById(id);
		if (!courseFinded.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, COURSE_ID_NOT_FOUND);
		}
		return courseFinded.get();
	}

	@Override
	public Long saveCourse(Course course) {
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setCode(course.getCode());
		courseEntity.setName(course.getName());

		CourseEntity savedCourse = courseRepository.save(courseEntity);
		return savedCourse.getId();
	}

	@Override
	public Long updateCourse(Long id, Course course) {

		CourseEntity courseToModify = getCourse(id);

		courseToModify.setCode(course.getCode());
		courseToModify.setName(course.getName());

		return saveCourse(courseToModify);
	}

	@Override
	public Long deleteCourse(Long id) {
		getCourse(id);

		courseRepository.deleteById(id);

		return id;
	}

}
