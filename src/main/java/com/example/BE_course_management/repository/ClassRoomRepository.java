package com.example.BE_course_management.repository;

import com.example.BE_course_management.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom,String> {

    boolean existsByRoomNumber(String roomNumber);

}
