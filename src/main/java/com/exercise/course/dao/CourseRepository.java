package com.exercise.course.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.exercise.course.model.CourseEntity;

public interface CourseRepository extends PagingAndSortingRepository<CourseEntity, Long> {

}
