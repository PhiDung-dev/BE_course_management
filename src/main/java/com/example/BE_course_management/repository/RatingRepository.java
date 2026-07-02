package com.example.BE_course_management.repository;

import com.example.BE_course_management.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating,String> {
}
