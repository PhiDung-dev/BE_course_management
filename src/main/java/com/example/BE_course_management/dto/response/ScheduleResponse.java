package com.example.BE_course_management.dto.response;

import com.example.BE_course_management.entity.ClassRoom;
import com.example.BE_course_management.entity.Course;
import com.example.BE_course_management.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleResponse {
    String id;
    LocalTime startTime;
    LocalTime endTime;
    DayOfWeek dayOfWeek;
    LocalDate startDate;
    LocalDate endDate;
    ClassRoom classRoom;
    CourseResponse course;
    UserResponse teacher;
}
