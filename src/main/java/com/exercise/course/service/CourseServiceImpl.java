package com.exercise.course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exercise.course.dao.CourseRepository;
import com.exercise.course.exception.CourseNotFoundException;
import com.exercise.course.model.Course;
import com.exercise.course.model.CourseEntity;

/**
 * Service in charge of course operations
 * @author Luis San Martin
 *
 */
@Service
public class CourseServiceImpl implements CourseService {

	private static final String COURSE_ID_NOT_FOUND = "Course id not found";
	@Autowired
	private CourseRepository courseRepository;

	/**
	 * allows to obtain courses on pages
	 */
	@Override
	public Page<CourseEntity> getPaginatedCourses(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
		return courseRepository.findAll(pageable);
	}

	/**
	 * allows to obtain all courses 
	 */
	@Override
	public List<CourseEntity> getCourses() {
		List<CourseEntity> courses = new ArrayList<>();
		courseRepository.findAll().forEach(courses::add);
		return courses;
	}

	/**
	 * allows to obtain a course by id
	 */
	@Override
	public CourseEntity getCourse(Long id) {
		Optional<CourseEntity> courseFinded = courseRepository.findById(id);
		if (!courseFinded.isPresent()) {
			throw new CourseNotFoundException(COURSE_ID_NOT_FOUND);
		}
		return courseFinded.get();
	}

	/**
	 * allows to save a new course
	 */
	@Override
	public Long saveCourse(Course course) {
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setCode(course.getCode());
		courseEntity.setName(course.getName());

		CourseEntity savedCourse = courseRepository.save(courseEntity);
		return savedCourse.getId();
	}

	/**
	 * allows to update a course by id 
	 */
	@Override
	public Long updateCourse(Long id, Course course) {

		CourseEntity courseToModify = getCourse(id);

		courseToModify.setCode(course.getCode());
		courseToModify.setName(course.getName());

		CourseEntity savedCourse = courseRepository.save(courseToModify);

		return savedCourse.getId();
	}

	/**
	 * allows to delete a course by id
	 */
	@Override
	public Long deleteCourse(Long id) {
		getCourse(id);

		courseRepository.deleteById(id);

		return id;
	}

}
