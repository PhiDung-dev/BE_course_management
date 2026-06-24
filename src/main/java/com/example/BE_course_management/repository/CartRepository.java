package com.example.BE_course_management.repository;

import com.example.BE_course_management.entity.Cart;
import com.example.BE_course_management.entity.Course;
import com.example.BE_course_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,String> {
    boolean existsByUserAndCourse(User user, Course course);
    List<Cart> findByUserId(String userId);
    Cart findByUserIdAndCourseTitle(String userId, String courseTitle);

}
