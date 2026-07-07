package com.example.BE_course_management.repository;

import com.example.BE_course_management.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,String> {

    List<Schedule> findByTeacherId(String userId);

    List<Schedule> findByClassRoomId(String classRoomId);

    List<Schedule> findAllByBookingsUserId(String userId);

    boolean existsByClassRoomIdAndDayOfWeekAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            String classRoomId,
            DayOfWeek dayOfWeek,
            LocalDate startDate,
            LocalDate endDate,
            LocalTime startTime,
            LocalTime endTime
    );

}
