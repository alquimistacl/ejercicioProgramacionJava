package com.exercise.course.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.exercise.course.model.CourseEntity;

/**
 * Repository to handle the course db calls
 * @author Luis San Martin
 *
 */
public interface CourseRepository extends PagingAndSortingRepository<CourseEntity, Long> {

}
