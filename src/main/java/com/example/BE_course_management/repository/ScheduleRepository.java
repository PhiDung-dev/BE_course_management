package com.example.BE_course_management.repository;

import com.example.BE_course_management.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

    //kiem tra co trung lich hay khong
public interface ScheduleRepository extends JpaRepository<Schedule,String> {
    @Query("SELECT COUNT(s) > 0 FROM Schedule s WHERE s.classRoom.id = :classRoomId " +
            "AND s.dayOfWeek = :dayOfWeek " +
            "AND :startDate <= s.endDate AND :endDate >= s.startDate " +
            "AND :startTime < s.endTime AND :endTime > s.startTime")
    boolean existsConflictByClassRoom(@Param("classRoomId") String classRoomId,
                                      @Param("dayOfWeek") DayOfWeek dayOfWeek,
                                      @Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate,
                                      @Param("startTime") LocalTime startTime,
                                      @Param("endTime") LocalTime endTime);

    //Kiem tra lich giang vien co trung hay khong
    @Query("SELECT COUNT(s) > 0 FROM Schedule s WHERE s.teacher.id = :teacherId " +
            "AND s.dayOfWeek = :dayOfWeek " +
            "AND :startDate <= s.endDate AND :endDate >= s.startDate " +
            "AND :startTime < s.endTime AND :endTime > s.startTime")
    boolean existsConflictByTeacher(@Param("teacherId") String teacherId,
                                    @Param("dayOfWeek") DayOfWeek dayOfWeek,
                                    @Param("startDate") LocalDate startDate,
                                    @Param("endDate") LocalDate endDate,
                                    @Param("startTime") LocalTime startTime,
                                    @Param("endTime") LocalTime endTime);
}
