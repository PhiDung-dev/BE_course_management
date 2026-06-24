package com.example.BE_course_management.repository;

import com.example.BE_course_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
