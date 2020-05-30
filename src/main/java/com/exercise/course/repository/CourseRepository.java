package com.exercise.course.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.exercise.course.model.Course;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

	@Modifying
	@Query("update Course c set c.name = ?1, c.code = ?2 where c.id = ?3")
	int updateCourseById(String name, String code, Long id);
}
