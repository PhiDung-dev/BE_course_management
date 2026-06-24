package com.example.BE_course_management.repository;

import com.example.BE_course_management.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,String> {
}
