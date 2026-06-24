package com.example.BE_course_management.repository;

import com.example.BE_course_management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
    boolean existsByTitle(String title);
}
