package com.example.BE_course_management.dto.response;

import com.example.BE_course_management.entity.ClassRoomStatus;
import com.example.BE_course_management.entity.Schedule;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassRoomResponse {
    String id;
    String roomNumber;
    ClassRoomStatus status;
    List<Schedule> schedules;
}
